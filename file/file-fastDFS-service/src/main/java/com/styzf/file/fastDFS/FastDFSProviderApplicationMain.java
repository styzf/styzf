package com.styzf.file.fastDFS;

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
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication()
@ComponentScan(basePackages = { "com.styzf.file.fastDFS","com.styzf.springboot.mybatisPlus" })
//@MapperScan("com.styzf.test.mapper*")
@EnableCaching
@EnableAsync
//@ImportResource({"styzf-fastDFS-service-dubbo.xml"})
public class FastDFSProviderApplicationMain {
    static final Log logger = LogFactory.getLog(FastDFSProviderApplicationMain.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FastDFSProviderApplicationMain.class, args);
        logger.info(StringUtils.rightPad("*", 80, "*"));
        logger.info(StringUtils.center(" styzf-file-fastDFS-provider start success! ", 80, "*"));
        logger.info(StringUtils.rightPad("*", 80, "*"));
    }



}