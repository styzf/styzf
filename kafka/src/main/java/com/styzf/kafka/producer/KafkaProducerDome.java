package com.styzf.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerDome extends Thread{

    private final KafkaProducer<Integer, String> producer;
    
    private final String topic;

    public KafkaProducerDome(String topic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
                "192.168.114.129:9092,192.168.114.130:9092,192.168.114.131:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDome");
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
                "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
                "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
        
        this.topic = topic;
    }
    
    @Override
    public void run() {
        int num = 0;
        while (num < 50) {
            String msg = "msg_" + num;
            producer.send(new ProducerRecord<Integer, String>(topic, msg));
            System.out.println("begin send msg:" + msg);
            num ++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new KafkaProducerDome("test1").start();
    }
}
