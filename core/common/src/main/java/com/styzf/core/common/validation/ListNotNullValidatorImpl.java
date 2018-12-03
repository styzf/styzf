package com.styzf.core.common.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

import com.styzf.core.common.util.Assert;
import com.styzf.core.common.validation.annotation.ListNotNull;
 
/**
 * 演示实现ListNotHasNull校验注解的实现类
 *
 * @author LIUTAO
 * @version 2017/5/19
 * @see
 */
@Service
public class ListNotNullValidatorImpl implements ConstraintValidator<ListNotNull, List<?>> {
 
    private int value;
    private String errorKey;
    private Object[] params;
    private String message;
    
    @Override
    public void initialize(ListNotNull constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
        this.errorKey = constraintAnnotation.errorKey();
        this.params = constraintAnnotation.params();
        // 信息不为空的话，直接使用该信息就行报错
        this.message = constraintAnnotation.message();
    }
 
    public boolean isValid(List<?> list, ConstraintValidatorContext constraintValidatorContext) {
        if (list == null || list.size() < 1) {
            Assert.throwException(errorKey);
        }
        return true;
    }

}