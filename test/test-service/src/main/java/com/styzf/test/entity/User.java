package com.styzf.test.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.mapper.SqlCondition;
import com.styzf.springboot.mybatisPlus.entity.BaseEntity;

/**
 * 用户表
 */
@SuppressWarnings("serial")
@TableName("user")
public class User extends BaseEntity<User> {

    @TableId(value = "test_id", type = IdType.ID_WORKER)
    private String id;
    /**
     * 名称 , condition 属性设置注入
     * 等效于 SQL 为：WHERE name LIKE CONCAT('%',s值,'%')
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    /**
     * update 时候注入年龄 + 1
     * 等效于 SQL 为： update user set age=age+1
     */
    @TableField(update = "%s+1")
    private Integer age;
    /**
     * 这里故意演示注解可无
     */
    @TableField("test_type")
    @TableLogic
    private Integer testType;

    /**
     * 这里等效于更新为系统时间
     */
    @TableField(value = "test_date",update = "now()")
    private Date testDate;

    private Long role;
    private String phone;

    @TableField(value = "tenant_id")
    private Long tenantId;
    
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
