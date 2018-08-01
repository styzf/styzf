package com.styzf.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.styzf.test.dto.TestMainDTO;
import com.styzf.test.service.ITestService;

@Controller
@RequestMapping(value = "/styzfTestController")
public class StyzfTestController {

    @Autowired
    private ITestService testService;
    
	@RequestMapping(value = "hello", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String hello() {
		return testService.test();
	}
	
	@RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Boolean insert(@RequestParam String name) {
	    return testService.insert(name);
	}
	
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<TestMainDTO> list() {
	    return testService.list();
	}
	
}