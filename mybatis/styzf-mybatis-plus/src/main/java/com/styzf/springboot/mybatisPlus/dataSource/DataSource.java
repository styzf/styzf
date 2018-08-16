package com.styzf.springboot.mybatisPlus.dataSource;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * 数据源配置注解，使用该注解可以指定不同的数据节点，还可以指定是否是只读库
 * @author styzf
 * @date 2018年8月1日 
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
  String nodeName() default "";
  
  boolean readOnly() default false;
}
