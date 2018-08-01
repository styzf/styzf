package com.styzf.core.common.test;

import com.styzf.core.common.util.OrikaUtil;

public class OrikaTest {
    public static void main(String[] args) {
        A a = new A();
        a.setAge(1);
        a.setName("styzf");
        B map = OrikaUtil.map(a, B.class);
        System.out.println(map);
    }
}

class A {
    Integer age;
    String name;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

class B{
    Integer age;
    String name;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}