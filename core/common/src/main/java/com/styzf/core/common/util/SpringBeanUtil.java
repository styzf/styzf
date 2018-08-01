package com.styzf.core.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtil implements ApplicationContextAware, DisposableBean{

    private static ApplicationContext applicationContext = null;
    
    private static Logger logger = LoggerFactory.getLogger(SpringBeanUtil.class);
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.debug("注入ApplicationContext到SpringContextHolder:" + applicationContext);
        
        if (applicationContext != null) {
          logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
        }
        
        SpringBeanUtil.applicationContext = applicationContext;
    }
    
    @Override
    public void destroy() throws Exception {}
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public static <T> T getBean(String name) {
        if (applicationContext == null)
           return null;
        return (T)applicationContext.getBean(name);
    }
    
    
    
    public static <T> T getBean(Class<T> requiredType) {
        if (applicationContext == null) {
            return null;
        }
        return (T)applicationContext.getBean(requiredType);
    }
    
    public static void clear() {
        logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }
    
    public static <T> List<T> getBeansForType(Class<T> type) {
        if (applicationContext == null)
            return null;
        String[] beanNameArr = applicationContext.getBeanNamesForType(type);
        List<T> beanNameList = (List<T>) Arrays.asList(beanNameArr);
        List<T> beanList = new ArrayList<>();
        for (int i = 0; i < beanNameArr.length; i++) {
            if (beanNameArr[i].endsWith("Target")) {
                String serviceBeanName = beanNameArr[i].substring(0, beanNameArr[i].length() - 6) + "Service";
                if (!beanNameList.contains(serviceBeanName))
                    beanList.add((T) applicationContext.getBean(beanNameArr[i]));
            } else {
                beanList.add((T) applicationContext.getBean(beanNameArr[i]));
            }
        }
        return beanList;
    }
    
    public static <T> List<T> getBeansForType(Class<T> type, String exceptBeanName)
    {
        if (applicationContext == null)
            return null;
        String[] beanNameArr = applicationContext.getBeanNamesForType(type);
        List<T> beanNameList = (List<T>) Arrays.asList(beanNameArr);
        List<T> beanList = new ArrayList<>();
        for (int i = 0; i < beanNameArr.length; i++) {
            if (!beanNameArr[i].equals(exceptBeanName)) {
                if (beanNameArr[i].endsWith("Target")) {
                  String serviceBeanName = beanNameArr[i].substring(0, beanNameArr[i].length() - 6) + "Service";
                  if (!beanNameList.contains(serviceBeanName))
                      beanList.add((T) applicationContext.getBean(beanNameArr[i]));
                } else {
                  beanList.add((T) applicationContext.getBean(beanNameArr[i]));
                } 
            }
        }
        return beanList;
    }
}
