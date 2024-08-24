package com.jupiter.db.kafka;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-09-17 01:02
 */

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class KafkaStreamsSplitExample {
    public static void main(String[] args) {
        // Kafka Streams配置
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "split-example");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ProducerConfig.ACKS_CONFIG, "all");

        // 创建StreamsBuilder对象
        StreamsBuilder builder = new StreamsBuilder();

        // 从“input-topic”输入主题中读取数据，并根据键值映射（KV）分组
//        KStream<String, String> inputStream = builder.<String, String>stream("input-topic",
//                        Consumed.with(Serdes.String(), Serdes.String()))
//                // 使用split方法根据键将流分成多个流
//                .split(new ValueMapper<String, Iterable<KeyValue<String, String>>>() {
//                    @Override
//                    public Iterable<KeyValue<String, String>> apply(String value) {
//                        List<KeyValue<String, String>> output = new ArrayList<>();
//                        for (String key : value.split(":")) {
//                            output.add(new KeyValue<String, String>(key, value));
//                        }
//                        return output;
//                    }
//                }, NamedType.<String, KeyValue<String, String>>withName("split-stream-"))
//                // 将每个子流的数据发送到对应的主题
//                .map(new ValueMapperWithKey<String, KeyValue<String, String>, KeyValue<String, String>>() {
//                    @Override
//                    public KeyValue<String, String> apply(String key, KeyValue<String, String> value) {
//                        return new KeyValue<>(key + value.key, value.value);
//                    }
//                }, Produced.<String, String>asynchronous());

        // 构建Kafka Streams应用程序并启动它
//        KafkaStreams streams = builder.<String, String>build(config);
//        streams.start();
    }
}
