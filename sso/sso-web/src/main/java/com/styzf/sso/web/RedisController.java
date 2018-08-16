package com.styzf.sso.web;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.styzf.core.common.redis.RedisUtil;
import com.styzf.core.common.util.IdWorker;
import com.styzf.sso.aspect.validated.annotation.RegisterValidated;
import com.styzf.sso.constant.SSOConstant;
import com.styzf.sso.dto.UserDto;
import com.styzf.sso.dto.UserInfo;
import com.styzf.sso.service.IStyzfUserService;
import com.styzf.sso.util.CookieUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * redis操作
 * </p>
 *
 * @author styzf
 * @since 2018-08-14
 */
@Api(value = "redis操作")
@RestController
@RequestMapping(value = "/redis", method = {RequestMethod.GET, RequestMethod.POST})
public class RedisController {
    
    @Autowired
    private RedisUtil redisUtil;
    
    @ApiOperation(value = "get", notes = "get")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "key", value = "key", required = true, paramType = "form", dataType = "string"),
    })
    @RegisterValidated
    @RequestMapping("get")
    public String get(String key) {
        return redisUtil.get(key);
    }
    
    @ApiOperation(value = "keys", notes = "keys")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pattern", value = "pattern", required = true, paramType = "form", dataType = "string"),
    })
    @RegisterValidated
    @RequestMapping("keys")
    public Set<String> keys(String pattern) {
        return redisUtil.keys(pattern);
    }
 
}

