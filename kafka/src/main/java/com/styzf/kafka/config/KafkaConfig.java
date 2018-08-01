package com.styzf.kafka.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

/**
 * kafka配置类
 * @author styzf
 * @date 2018年7月30日 
 */
@Component
@EnableKafka
@Configuration
@ConfigurationProperties(prefix = "styzf.config.kafka")
public class KafkaConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);
    
    private static final String ESCAPE_SYMBOL = "-";
    
    /** producer端配置信息 */
    private Map<String, Object> producer;
    
    /** consumer端配置信息 */
    private Map<String, Object> consumer;
    
    /** Kafka链接地址 */
    private String servers;
    
    /**
     * Kafka消费者订阅主题-Service Bean Id
     */
    private List<ConsumerTopicConfig> consumerTopicConfig;
    
    @Bean(name = "kafkaProducer")
    public KafkaProducer<String, String> kafkaProducer() {
        logger.info(StringUtils.center("start init kafka producer", 80, "*"));
        Map<String, Object> props = new HashMap<String, Object>();
        if(producer == null) {
            /** 默认配置 */
            props.put(ProducerConfig.RETRIES_CONFIG, 0);
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
            props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        } else {
            /** 配置信息详见ProducerConfig，支持-进行配置 */
            for (String key : producer.keySet()) {
                Object value = producer.get(key);
                String escapekey = key.replaceAll(ESCAPE_SYMBOL, ".");
                props.put(escapekey, value);
            }
        }
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.servers);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        logger.info(StringUtils.center("end init kafka producer", 80, "*"));
        return kafkaProducer;
    }

    @Bean(name = "kafkaConsumer")
    public KafkaConsumer<String, String> kafkaConsumer() {
        logger.info(StringUtils.center("start init kafka consumer", 80, "*"));
        Map<String, Object> props = new HashMap<String, Object>();
        if(consumer == null) {
            /** 默认配置 */
            props = new HashMap<String, Object>();
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "calendar-group");
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1000");
        } else {
            /** 配置信息详见ConsumerConfig，支持-进行配置 */
            for (String key : consumer.keySet()) {
                Object value = consumer.get(key);
                String escapekey = key.replaceAll(ESCAPE_SYMBOL, ".");
                props.put(escapekey, value);
            }
        }
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.servers);
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        if(consumerTopicConfig != null && consumerTopicConfig.size() > 0) {
            List<String> topics = new ArrayList<>();
            for (ConsumerTopicConfig ctc : consumerTopicConfig) {
                if(!topics.contains(ctc.getTopic())) {
                    topics.add(ctc.getTopic());
                }
            }
            logger.info("kafka subscribe topics : " + topics);
            kafkaConsumer.subscribe(topics);
        } else {
            logger.error("kafka hasn't subscribe any topics!");
        }
        logger.info(StringUtils.center("end init kafka consumer", 80, "*"));
        return kafkaConsumer;
    }
    
    @PreDestroy
    public void destroy() {
        logger.info("start destroy kafka configuartion");
        kafkaProducer().close();
        kafkaConsumer().close();
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public List<ConsumerTopicConfig> getConsumerTopicConfig() {
        return consumerTopicConfig;
    }
    
    public void setConsumerTopicConfig(List<ConsumerTopicConfig> consumerTopicConfig) {
        this.consumerTopicConfig = consumerTopicConfig;
    }
    
    public ConsumerTopicConfig getTopicConfig(String topic) {
        ConsumerTopicConfig cTopicConfig = null;
        if(consumerTopicConfig != null && consumerTopicConfig.size() > 0) {
            for (ConsumerTopicConfig ctc : consumerTopicConfig) {
                if(topic.equals(ctc.getTopic())) {
                    cTopicConfig = ctc;
                    break;
                }
            }
        }
        return cTopicConfig;
    }
    
    /**
     * 内部类，用于配置Topic信息
     * @author styzf
     * @date 2018年7月31日 
     *
     */
    public static class ConsumerTopicConfig implements Serializable {

        private static final long serialVersionUID = 1L;
        
        /**
         * 主题
         */
        private String topic;
        
        /**
         * 主题解析Bean ID
         */
        private String beanId;
        
        /**
         * 发送返回结果主题
         */
        private String responseTopic;
        
        /**
         * 需转发主题
         */
        private List<String> sendTopics;
        
        public String getTopic() {
            return topic;
        }
        
        public void setTopic(String topic) {
            this.topic = topic;
        }
        
        public String getBeanId() {
            return beanId;
        }
        
        public void setBeanId(String beanId) {
            this.beanId = beanId;
        }
       
        public String getResponseTopic() {
            return responseTopic;
        }
        
        public void setResponseTopic(String responseTopic) {
            this.responseTopic = responseTopic;
        }
        
        public List<String> getSendTopics() {
            return sendTopics;
        }
        
        public void setSendTopics(List<String> sendTopics) {
            this.sendTopics = sendTopics;
        }
    }
}
