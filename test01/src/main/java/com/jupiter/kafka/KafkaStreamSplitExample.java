package com.jupiter.kafka;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-09-17 01:12
 */

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class KafkaStreamSplitExample {
    public static void main(String[] args) {
        // Set up the Kafka Streams configuration
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("application.id", "kafka-stream-split-example");

        // Create a StreamsBuilder
        StreamsBuilder builder = new StreamsBuilder();

        // Create a KStream from the "input-topic"
        KStream<String, String> inputTopicStream = builder.stream("input-topic");

//        // Split the stream based on the key and distribute to different topics
//        KStream<String, String>[] splitStreams = inputTopicStream.split((key, value) -> {
//            String topicName = key + "-topic"; // Append key to topic prefix
//            return new String[]{topicName};
//        }, Named.as("split-stream"));
//
//        // Forward each split stream to the corresponding topic
//        for (int i = 0; i < splitStreams.length; i++) {
//            splitStreams[i].to(splitStreams[i].getOutputTopic());
//        }

        // Build the Kafka Streams topology
        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Start the Kafka Streams application
        streams.start();

        // Shutdown gracefully on termination
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}