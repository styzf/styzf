package com.styzf.sso.aspect.validated;

import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.styzf.core.common.util.Assert;
import com.styzf.core.common.validation.RegEx;
import com.styzf.sso.dto.UserDto;

/**
 * 验证统一处理
 * @author styzf
 * @date 2018年7月24日 
 *
 */
@Aspect
@Component
public class ValidatedAspect {
    protected static final Logger logger = LoggerFactory.getLogger(ValidatedAspect.class);
    
    @Autowired  
    HttpServletRequest request;
    
    /**
     * 注册验证
     * @author styzf
     * @date 2018年8月16日
     */
    @Before("@annotation(com.styzf.sso.aspect.validated.annotation.RegisterValidated)")
    public void beforeRegisterValidated(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length < 1) {
            Assert.throwException("errorKey.30000");
        }
        IntStream.range(0, args.length).parallel().forEach(index -> {
            Object para = args[index];
            if (para instanceof UserDto) {
                
                UserDto userDto = (UserDto) para;
                String stPhone = userDto.getStPhone();
                String stPassword = userDto.getStPassword();
                String code = userDto.getCode();
                if (StringUtils.isBlank(stPhone) || 
                        StringUtils.isBlank(stPassword) || 
                            StringUtils.isBlank(code)) {
                    Assert.throwException("errorKey.30000");
                }
                if (StringUtils.isBlank(stPhone) || stPhone.matches(RegEx.Phone.getContext())) {
                    Assert.throwException("errorKey.30001");
                }
                if (StringUtils.isBlank(stPassword)) {
                    Assert.throwException("errorKey.30002");
                }
                if (StringUtils.isBlank(code)) {
                    Assert.throwException("errorKey.30003");
                }
            }
        });
    }
    
}
