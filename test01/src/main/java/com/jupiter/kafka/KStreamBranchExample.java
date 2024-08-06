package com.jupiter.kafka;

/**
 * @author Jupiter.Lin
 * @desc test kafka stream branch
 * @date 2023-09-16 23:52
 */

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KStreamBranchExample {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "split-example");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> inputStream = builder.stream("input-topic");

        // 过滤数据
        // KStream filteredStream = stream.filter((key, value) -> value.contains("filter"));
        // filteredStream.to("filtered-topic");
        // KafkaStreams streams = new KafkaStreams(builder.build(), props);
        // streams.start();

        List<String> indexList = Arrays.asList("00001", "00080", "10068");

        Predicate[] predicates = new Predicate[indexList.size()];
        for (int i = 0;i< indexList.size();i++) {
            int finalI = i;
            predicates[i] = new Predicate<String, String>() {
                @Override
                public boolean test(String k, String v) {
                    return k != null && k.startsWith(indexList.get(finalI));
                }
            };
        }

        // 分流数据
        KStream[] branches = inputStream.branch(predicates);
        for (int j = 0; j < branches.length; j++) {
            branches[j].to("index-" + indexList.get(j) + "-topic");
        }

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}

class KStreamBranchExample2 {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> inputStream = builder.stream("input-topic");

        List<String> indexList = Arrays.asList("00001", "00080", "10068");

        Predicate<String, String> predicate1 = (key, value) -> key != null && key.startsWith(indexList.get(0));
        Predicate<String, String> predicate2 = (key, value) -> key != null && key.startsWith(indexList.get(1));
        Predicate<String, String> predicate3 = (key, value) -> key != null && key.startsWith(indexList.get(2));

        KStream<String, String> branchStream1 = inputStream.filter(predicate1);
        KStream<String, String> branchStream2 = inputStream.filter(predicate2);
        KStream<String, String> branchStream3 = inputStream.mapValues(value ->{
            return value.toUpperCase();}) // 将值转换为大写
                .filter(predicate3); // 过滤

        branchStream1.to("indexNo" + indexList.get(0) + "-topic");
        branchStream2.to("indexNo" + indexList.get(1) + "-topic");
        branchStream3.to("indexNo" + indexList.get(2) + "-topic");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        // 添加关闭钩子以在程序终止时关闭流
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}