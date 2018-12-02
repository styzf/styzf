package com.styzf.core.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.styzf.core.common.util.Assert;
import com.styzf.core.common.validation.annotation.NotNull;

public class NotNullValidatorImpl implements ConstraintValidator<NotNull, Object> {

    private int value;
    private String errorKey;
    private Object[] params;
    private String message;
    
    @Override
    public void initialize(NotNull constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
        this.errorKey = constraintAnnotation.errorKey();
        this.params = constraintAnnotation.params();
        // 信息不为空的话，直接使用该信息就行报错
        this.message = constraintAnnotation.message();
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            Assert.throwException(errorKey, params);
        }
        return Boolean.TRUE;
    }

}
