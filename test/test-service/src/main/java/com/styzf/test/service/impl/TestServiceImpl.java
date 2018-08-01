package com.styzf.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.styzf.test.dto.TestMainDTO;
import com.styzf.test.entity.TestMain;
import com.styzf.test.mapper.TestMainMapper;
import com.styzf.test.service.ITestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements ITestService{
    @Autowired
    private TestMainMapper testMainMapper;
    
    @Override
    public String test() {
        return "fuck";
    }

    @Override
    public boolean insert(String name) {
        TestMain styzfTestMain = new TestMain();
        styzfTestMain.setTestName(name);
        styzfTestMain.setTestAge(18);
        testMainMapper.insert(styzfTestMain);
        return Boolean.TRUE;
    }

    @Override
    public List<TestMainDTO> list() {
        List<TestMain> list = testMainMapper.selectByExample(null);
        List<TestMainDTO> dtoList = list.parallelStream().map(data -> {
            TestMainDTO dto = new TestMainDTO();
            BeanUtils.copyProperties(data, dto);
            return dto;
        }).collect(Collectors.toList());
        return dtoList;
    }

}
