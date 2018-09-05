package com.styzf.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
//@ConfigurationProperties(prefix = "spring.redis")
public class AppConfig {

    /**
     * Type safe representation of application.properties
     */
    @Autowired 
    private ClusterConfigurationProperties clusterProperties;
    
    private RedisConnectionFactory redisConnectionFactory;
    
    @Bean("redisConnectionFactory")
    public RedisConnectionFactory connectionFactory() {
        RedisPassword redisPassword = RedisPassword.of(clusterProperties.getPassword());
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterProperties.getNodes());
        redisClusterConfiguration.setPassword(redisPassword);
        this.redisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        redisClusterConfiguration.setPassword(redisPassword);
        return redisConnectionFactory;
    }
    
    @Bean("redisTemplate")
    @DependsOn({"redisConnectionFactory"})
    public RedisTemplate<?, ?> redisTemplate() {
      RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
      template.setConnectionFactory(redisConnectionFactory);
      return template;
    }
    
    @Bean("stringRedisTemplate")
    @DependsOn({"redisConnectionFactory"})
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return stringRedisTemplate;
    }

//    @Autowired
//    private RedisConfigurationProperties properties;
//    
//    @Bean("redisConnectionFactory")
//    @SuppressWarnings("deprecation")
//    public RedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(properties.getHost());
//        jedisConnectionFactory.setPort(properties.getPort());
//        jedisConnectionFactory.setPassword(properties.getPassword());
//        return jedisConnectionFactory;
//    }
    
}
