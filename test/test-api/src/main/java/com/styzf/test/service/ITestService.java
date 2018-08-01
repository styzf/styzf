package com.styzf.test.service;

import java.util.List;

import com.styzf.test.dto.TestMainDTO;

public interface ITestService {
    public String test();
    public boolean insert(String name);
    public List<TestMainDTO> list();
}
