package com.styzf.springboot.mybatisPlus.dataSource;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class DataSourceAspect {
    protected static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
    
    @Before("@within(com.styzf.springboot.mybatisPlus.dataSource.DataSource) || @annotation(com.styzf.springboot.mybatisPlus.dataSource.DataSource)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        DataSource dataSource = (DataSource)method.getAnnotation(DataSource.class);
        if (dataSource == null) {
            dataSource = (DataSource)method.getDeclaringClass().getAnnotation(DataSource.class);
        }
        
        DataSourceClusterManager.set(dataSource.nodeName(), dataSource.readOnly());
    }
}
