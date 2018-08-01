package com.styzf.core.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 继承org.springframework.beans.BeanUtils，增加部分转换方法
 * @author styzf
 * @date 2018年7月27日 
 *
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    protected static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    
/*    public static Object readFieldByPath(Object target, String fieldPath) {
      try {
          String[] fieldPaths = fieldPath.split("\\.");
          
          PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(target, fieldPaths[0]);
          Method readMethod = propertyDescriptor.getReadMethod();
          Object value = readMethod.invoke(target, new Object[0]);
          
          if ((value != null) && (fieldPaths.length > 1)) {
              return readFieldByPath(value, fieldPath.substring(fieldPaths[0].length() + 1));
          }
          return value;
      } catch (Exception e) {
          logger.error("", e); 
      }
      return null;
    }*/

    public static <T> List<T> copyPropertiesByList(List<?> sourceList, Class<T> targetClass) {
        try {
            if (sourceList != null) {
                List<T> newList = new ArrayList<>();
                sourceList.parallelStream().forEach(obj -> {
                    T target = null;
                    try {
                        target = targetClass.newInstance();
                    } catch (Exception e) {
                        logger.error("", e);
                        throw new RuntimeException(e);
                    }
                    org.springframework.beans.BeanUtils.copyProperties(obj, target);
                    newList.add(target);
                });
                return newList;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }
    
    public static Map<String, Object> transBean2Map(Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            
            if (!key.equals("class"))  {
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj, new Object[0]);
                
                map.put(key, value);
            }
        }
        
        return map;
    }
}
