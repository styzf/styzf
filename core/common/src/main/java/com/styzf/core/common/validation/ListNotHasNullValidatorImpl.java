package com.styzf.core.common.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.styzf.core.common.util.Assert;
import com.styzf.core.common.validation.annotation.ListNotHasNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
 
/**
 * 演示实现ListNotHasNull校验注解的实现类
 *
 * @author LIUTAO
 * @version 2017/5/19
 * @see
 */
@Service
public class ListNotHasNullValidatorImpl implements ConstraintValidator<ListNotHasNull, List<?>> {
 
    private int value;
    private String errorKey;
    private Object[] params;
    private String message;
    
    @Override
    public void initialize(ListNotHasNull constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
        this.errorKey = constraintAnnotation.errorKey();
        this.params = constraintAnnotation.params();
        // 信息不为空的话，直接使用该信息就行报错
        this.message = constraintAnnotation.message();
    }
 
    public boolean isValid(List<?> list, ConstraintValidatorContext constraintValidatorContext) {
        if (list == null) {
            Assert.throwException("errorKey.10000");
            return false;
        }
        list.parallelStream().forEach(object -> {
            if (object == null) {
                //如果List集合中含有Null元素，校验失败
                if (StringUtils.isNotBlank(message)) {
                    Assert.throwException(errorKey, message, params);
                }
                Assert.throwException(errorKey, params);
            }
        });
        return true;
    }

}