package com.styzf.message;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication()
@ComponentScan(basePackages = { "com.styzf"})
@MapperScan("com.styzf.message.mapper*")
@EnableCaching
@EnableAsync
@ImportResource({"styzf-test-service-dubbo.xml"})
public class MessageServiceApplicationMain {
    static final Log logger = LogFactory.getLog(MessageServiceApplicationMain.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MessageServiceApplicationMain.class, args);
        logger.info(StringUtils.rightPad("*", 80, "*"));
        logger.info(StringUtils.center(" styzf-message-service start success! ", 80, "*"));
        logger.info(StringUtils.rightPad("*", 80, "*"));
    }

}
