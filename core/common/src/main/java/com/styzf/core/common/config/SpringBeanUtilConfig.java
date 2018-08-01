package com.styzf.core.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.styzf.core.common.util.SpringBeanUtil;

@Configuration
public class SpringBeanUtilConfig {
    protected final Logger logger = LoggerFactory.getLogger(SpringBeanUtilConfig.class);
    
    @Bean
    public SpringBeanUtil springBeanUtil() {
        SpringBeanUtil springBeanUtil = new SpringBeanUtil();
        return springBeanUtil;
    }
}
