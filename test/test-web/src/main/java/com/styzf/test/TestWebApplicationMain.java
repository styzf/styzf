package com.styzf.test;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ImportResource(locations={"classpath:styzf-test-web-dubbo.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.styzf.test" , "com.styzf.core.common" })
@EnableCaching
@EnableAsync
@SpringBootApplication
public class TestWebApplicationMain {
    private static Logger logger = Logger.getLogger(TestWebApplicationMain.class);
    
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(TestWebApplicationMain.class);
		springApplication.run(args);
        logger.info(StringUtils.rightPad("*", 80, "*"));
        logger.info(StringUtils.center(" styzf-test-web start success! ", 80, "*"));
        logger.info(StringUtils.rightPad("*", 80, "*"));
	}
}