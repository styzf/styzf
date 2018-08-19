package com.styzf.kafka.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerDomeFind extends Thread{
    
    private final KafkaConsumer<Integer, String> consumer;
    
    public KafkaConsumerDomeFind(String topic) {
        
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
                "192.168.114.129:9092,192.168.114.130:9092,192.168.114.131:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "find");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
                "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));
    }
    
    @Override
    public void run() {
        int num = 0;
        a:
        while (true) {
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000);
            System.out.println(consumerRecords.count());
            for (ConsumerRecord<Integer, String> consumerRecord : consumerRecords) {
                System.out.println("msg:" + consumerRecord.value());
                num ++;
                System.out.println(num);
                if (num == 20) {
                    break a;
                }
            }
        }
        consumer.close();
    }
    
    public static void main(String[] args) {
        new KafkaConsumerDomeFind("test").start();
    }
}
