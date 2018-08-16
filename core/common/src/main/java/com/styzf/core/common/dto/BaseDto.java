package com.styzf.core.common.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class BaseDto implements Serializable{
    private static final long serialVersionUID = -4083754097244748607L;
    
    /**
     * 创建人id
     */
    private String styzfCreatorId;
    /**
     * 创建时间
     */
    private Date styzfCreateTime;
    /**
     * 最后更新人
     */
    private String styzfLastUpdateId;
    /**
     * 最后更新时间
     */
    private Date styzfLastUpdateTime;
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();
        sb.append(clazz.getName() + "{");
        try {
          for (Field field : fields) {
            field.setAccessible(true);
            String value = field.get(this) == null ? "" : field.get(this).toString();
            sb.append("\n  " + field.getName() + ":" + value);
          }
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        sb.append("\n}");
        
        return sb.toString();
    }
}
