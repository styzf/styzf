package com.styzf.springboot.mybatisPlus.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

public abstract class BaseEntity<T extends Model> extends Model<T> implements Serializable{
    /**
     * 创建人id
     */
    @TableField("styzf_creator_id")
    private String styzfCreatorId;
    /**
     * 创建时间
     */
    @TableField("styzf_create_time")
    private Date styzfCreateTime;
    /**
     * 最后更新人
     */
    @TableField("styzf_last_update_id")
    private String styzfLastUpdateId;
    /**
     * 最后更新时间
     */
    @TableField(value = "styzf_last_update_time")
    private Date styzfLastUpdateTime;
    
    public String getStyzfCreatorId() {
        return styzfCreatorId;
    }

    public void setStyzfCreatorId(String styzfCreatorId) {
        this.styzfCreatorId = styzfCreatorId;
    }

    public Date getStyzfCreateTime() {
        return styzfCreateTime;
    }

    public void setStyzfCreateTime(Date styzfCreateTime) {
        this.styzfCreateTime = styzfCreateTime;
    }

    public String getStyzfLastUpdateId() {
        return styzfLastUpdateId;
    }

    public void setStyzfLastUpdateId(String styzfLastUpdateId) {
        this.styzfLastUpdateId = styzfLastUpdateId;
    }

    public Date getStyzfLastUpdateTime() {
        return styzfLastUpdateTime;
    }

    public void setStyzfLastUpdateTime(Date styzfLastUpdateTime) {
        this.styzfLastUpdateTime = styzfLastUpdateTime;
    }

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
