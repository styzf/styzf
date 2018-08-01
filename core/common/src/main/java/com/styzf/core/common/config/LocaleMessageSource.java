package com.styzf.core.common.config;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.styzf.core.common.user.IGetLocaleInfo;
import com.styzf.core.common.util.SpringBeanUtil;

@Component
public class LocaleMessageSource {
    @Resource
    private MessageSource messageSource;
    
    private Locale getLocale() {
//        IGetLocaleInfo getLocaleInfo = (IGetLocaleInfo)SpringBeanUtil.getBean(IGetLocaleInfo.class);
//        if (getLocaleInfo != null) {
//            Locale locale = getLocaleInfo.getLocale();
//            return locale;
//        }
        
        return Locale.SIMPLIFIED_CHINESE;
    }
    
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }
    
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, getLocale());
    }
    
    public String getMessageByLocale(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }
    
    public String getMessage(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }
    
    public String getExtendMessage(String messageName, Locale locale) {
        if (StringUtils.isBlank(messageName)) {
          return "";
        }
        if (locale == null)
          locale = Locale.getDefault();
        String[] kv = messageName.split("%%");
        if (kv.length == 1) {
          String info = getMessageByLocale(kv[0], locale);
          if (StringUtils.isNotBlank(info)) {
            return info;
          }
          return messageName;
        }
        String[] keys = kv[1].split(";");
        String[] values = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
          values[i] = getMessageByLocale(keys[i], locale);
        }
        for (int i = 0; i < values.length; i++) {
          kv[0] = kv[0].replace("{" + i + "}", values[i]);
        }
        return kv[0];
    }
}
