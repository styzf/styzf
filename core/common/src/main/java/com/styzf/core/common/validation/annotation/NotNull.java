package com.styzf.core.common.validation.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;

import com.styzf.core.common.validation.EMailValidatorImpl;
import com.styzf.core.common.validation.NotNullValidatorImpl;

import java.lang.annotation.*;
 
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
 
/**
 * 
 * @author styzf
 * @date 2018年8月15日 
 *
 */
@Target({ANNOTATION_TYPE, METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NotNullValidatorImpl.class)
public @interface NotNull {
    /**
     * 添加value属性，可以作为校验时的条件,若不需要，可去掉此处定义
     */
    int value() default 0;
 
    String message() default "";
    
    String errorKey() default "errorKey.10001";
    
    String[] params() default {};
    
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    /**
     * 定义List，为了让Bean的一个属性上可以添加多套规则
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotNull[] value();
    }
}