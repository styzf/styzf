package com.styzf.core.common.util;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import com.styzf.core.common.config.LocaleMessageSource;
import com.styzf.core.common.exception.StyzfException;

public class Assert extends org.springframework.util.Assert {
    public static void throwException()
    {
      throw new StyzfException("styzf.system.error");
    }
    
    public static String getLocaleMsg(String errorKey, Object[] params) {
      LocaleMessageSource localeMessageSource = (LocaleMessageSource)SpringBeanUtil.getBean(LocaleMessageSource.class);
      String msg = "";
      try {
        msg = localeMessageSource.getMessage(errorKey, params);
      } catch (Exception e) {
        msg = errorKey;
      }
      return msg;
    }
    
    public static void throwException(String errorKey) {
      throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
    }
    
    public static void throwException(String errorKey, Throwable cause) {
      throw new StyzfException(errorKey, getLocaleMsg(errorKey, null), cause);
    }
    
    public static void throwException(String errorKey, Object[] params) {
      throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
    }

    public static void throwException(String errorKey, String errorMsg) {
        throw new StyzfException(errorKey, errorMsg);
    }

    public static void throwException(String errorKey, String errorMsg, Object[] params) {
        throw new StyzfException(errorKey, MessageFormat.format(errorMsg, params));
    }
    
    public static void throwException(String errorKey, Object[] params, Throwable cause) {
      throw new StyzfException(errorKey, getLocaleMsg(errorKey, params), cause);
    }
    
    public static void isTrue(boolean expression, String message) {
      if (!expression)
        throw new StyzfException(message, getLocaleMsg(message, null));
    }
    
    public static void isTrue(boolean expression, String message, Object[] params) {
      if (!expression)
        throw new StyzfException(message, getLocaleMsg(message, params));
    }
    
    public static void isTrue(boolean expression) {
      isTrue(expression, "[Assertion failed] - this expression must be true");
    }
    
    public static void notBlank(String str, String errorKey) {
      if (StringUtils.isBlank(str)) {
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
      }
    }
    
    public static void notBlank(String str, String errorKey, Object[] params) {
      if (StringUtils.isBlank(str)) {
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
      }
    }
    
    public static void notNull(Object object, String errorKey) {
      if (object == null)
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
    }
    
    public static void notNull(Object object, String errorKey, Object[] params) {
      if (object == null)
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
    }
    
    public static void notEmpty(Object[] array, String errorKey) {
      if (ObjectUtils.isEmpty(array))
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
    }
    
    public static void notEmpty(Object[] array, String errorKey, Object[] params) {
      if (ObjectUtils.isEmpty(array))
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
    }
    
    public static void noNullElements(Object[] array, String errorKey) {
      if (array != null)
        for (Object element : array)
          if (element == null)
            throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
    }
    
    public static void noNullElements(Object[] array, String errorKey, Object[] params) {
      if (array != null)
        for (Object element : array)
          if (element == null)
            throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
    }
    
    public static void notEmpty(Map<?, ?> map, String errorKey) {
      if (CollectionUtils.isEmpty(map))
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
    }
    
    public static void notEmpty(Map<?, ?> map, String errorKey, Object[] params) {
      if (CollectionUtils.isEmpty(map))
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
    }
    
    public static void notEmpty(Collection<?> collection, String errorKey) {
      if (CollectionUtils.isEmpty(collection))
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, null));
    }
    
    public static void notEmpty(Collection<?> collection, String errorKey, Object[] params) {
      if (CollectionUtils.isEmpty(collection))
        throw new StyzfException(errorKey, getLocaleMsg(errorKey, params));
    }
    
    public static void doesNotContain(String textToSearch, String substring, String message) {
      if ((StringUtils.isEmpty(textToSearch)) || (StringUtils.isEmpty(substring)) || 
        (!textToSearch.contains(substring)))
        return;
      throw new StyzfException(message, getLocaleMsg(message, null));
    }
    
    public static void doesNotContain(String textToSearch, String substring, String message, Object[] params) {
      if ((StringUtils.isEmpty(textToSearch)) || (StringUtils.isEmpty(substring)) || 
        (!textToSearch.contains(substring)))
        return;
      throw new StyzfException(message, getLocaleMsg(message, params));
    }
    
    public static void doesNotContain(String textToSearch, String substring) {
      doesNotContain(textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [" + 
        substring + 
        "]");
    }
    
    public static void isInstanceOf(Class<?> clazz, Object obj) {
      isInstanceOf(clazz, obj, "");
    }
    
    public static void isInstanceOf(Class<?> type, Object obj, String message) {
      notNull(type, "Type to check against must not be null");
      if (type.isInstance(obj)) {
        return;
      }
      if (StringUtils.isNotEmpty(message)) {
        throw new StyzfException(message, getLocaleMsg(message, null));
      }
      
    
      String errorMessage = "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type;
      throw new StyzfException(errorMessage, errorMessage);
    }
    
    public static void isInstanceOf(Class<?> type, Object obj, String message, Object[] params)
    {
        notNull(type, "Type to check against must not be null");
        if (type.isInstance(obj)) {
          return;
        }
        if (StringUtils.isNotEmpty(message)) {
          throw new StyzfException(message, getLocaleMsg(message, params));
        }
        
        
        String errorMessage = "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type;
        throw new StyzfException(errorMessage, errorMessage);
    }
}
