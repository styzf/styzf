package com.styzf.test.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.styzf.test.dto.UserDto;
import com.styzf.test.service.IUserReadOnlyService;
import com.styzf.test.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/userController")
@Api(value = "测试")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IUserReadOnlyService userReadOnlyService;
    
    @ApiOperation(value = "测试", notes = "测试1")
    @RequestMapping(value = "hello", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<UserDto> hello() {
        return userService.selectListBySQL();
    }

    @RequestMapping(value = "helloRead", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<UserDto> helloRead() {
        return userReadOnlyService.selectListBySQL();
    }
    
    @RequestMapping(value = "insert" , method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Boolean insert(UserDto dto) {
        return userService.insert(dto);
    }
    
    @RequestMapping(value = "test" , method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Date test(@RequestParam(value="date", required=true) Date date) {
        return date;
    }
}
