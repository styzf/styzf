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

@Controller
@RequestMapping(value = "/userController")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IUserReadOnlyService userReadOnlyService;
    
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
