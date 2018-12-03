package com.styzf.core.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.styzf.core.common.util.Assert;
import com.styzf.core.common.validation.annotation.EMail;
import com.styzf.core.common.validation.annotation.Phone;

public class PhoneValidatorImpl implements ConstraintValidator<Phone, String> {

    private int value;
    private String errorKey;
    private Object[] params;
    private String message;
    
    @Override
    public void initialize(Phone constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
        this.errorKey = constraintAnnotation.errorKey();
        this.params = constraintAnnotation.params();
        // 信息不为空的话，直接使用该信息就行报错
        this.message = constraintAnnotation.message();
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            Assert.throwException("errorKey.10001");
        }
        if (! value.matches(RegEx.Phone.getContext())) {
            Assert.throwException(errorKey);
        }
        return Boolean.TRUE;
    }

}
