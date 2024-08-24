package com.jupiter.db.kafka;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-09-17 14:36
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
public class KafkaProducerConsumerExample {

    public static final Properties producerProps = new Properties();
    private static final String TOPIC_NAME = "input-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "Jupiter";
    // producer properties
    static {
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    // consumer properties
    public static final Properties consumerProps = new Properties();
    static{
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        System.setProperty("java.net.preferIPv4Stack" , "true");

        // Configure producer properties

        //1. Create Kafka producer
        Producer<String, String> producer = new KafkaProducer<>(producerProps);

        //2. Send a message to the topic
        String message = "Hello Kafka!";
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null){
                    log.info("sent to topic:{}, partition:{}",metadata.topic(),metadata.topic());
                }
            }
        }).get();
        producer.flush();

        //1. Create Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        //2. Subscribe to the topic
        consumer.subscribe(Collections.singleton(TOPIC_NAME));

        //3. Consume messages from the topic
        Thread.sleep(2000);
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
        for (var r : records) {
            System.out.println("Received message: " + r.value());
        }


        // Close the consumer and producer
        consumer.close();
        producer.close();
    }

    public Set showTopics(Properties properties) throws ExecutionException, InterruptedException {
        TreeSet<String> topicsSet = new TreeSet<>();
        AdminClient adminClient = AdminClient.create(properties);
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions().listInternal(false);
        ListTopicsResult listTopicsResult = adminClient.listTopics(listTopicsOptions);
        KafkaFuture<Set<String>> names = listTopicsResult.names();
        for (String topicName : names.get()) {
            // System.out.println(topicName);
            topicsSet.add(topicName);
        }
        adminClient.close();

        return topicsSet;
    }

    public void consumerByOffset(){
        //1. Create Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(consumerProps);

        //2 Subscribe to the topic
        ArrayList<String> topicList = new ArrayList<>();
        topicList.add(TOPIC_NAME);
        consumer.subscribe(topicList);

        //3.1 get the topics,partitions info first before seeking to offset
        Set<TopicPartition> assignmentSet = consumer.assignment();

        //3.2 to ensure topics and partitions assignment completed, otherwise 3.3 do nothing
        while (assignmentSet.size() == 0){
            consumer.poll(Duration.ofMillis(500));
            assignmentSet = consumer.assignment();
        }

        //3.3 seek to the specific offset
        for (TopicPartition topicPartition : assignmentSet) {
            consumer.seek(topicPartition,0L);
        }

        //4. consumer msg
        while(true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                log.info("Receive msg:{}",record);
            }
        }
    }

}
