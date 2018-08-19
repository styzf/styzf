package com.styzf.sso;

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

/**
 * 
 * @author styzf
 * @date 2018年7月23日 
 * @since 1.0.0
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication()
@ComponentScan(basePackages = { "com.styzf"})
@MapperScan("com.styzf.sso.mapper*")
@EnableCaching
@EnableAsync
@ImportResource({"styzf-sso-service-dubbo.xml"})
public class SSOServiceApplicationMain {
    static final Log logger = LogFactory.getLog(SSOServiceApplicationMain.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SSOServiceApplicationMain.class, args);
        logger.info(StringUtils.rightPad("*", 80, "*"));
        logger.info(StringUtils.center(" styzf-sso-provider start success! ", 80, "*"));
        logger.info(StringUtils.rightPad("*", 80, "*"));
    }



}