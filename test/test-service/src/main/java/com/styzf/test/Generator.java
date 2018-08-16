package com.styzf.test;

import com.styzf.springboot.mybatisPlus.generator.MpGenerator;

/**
 * 自动生成
 * @author styzf
 * @date 2018年7月23日 
 *
 */
public class Generator {
    public static void main(String[] args) {
        MpGenerator.generatorMySQL("E:\\dome\\test\\test-service\\generator", 
                "jdbc:mysql://192.168.114.129:3306/styzf_test", "root", "root",
                "com.styzf", "test");
    }
}
