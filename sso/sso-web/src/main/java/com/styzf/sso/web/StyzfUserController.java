package com.styzf.sso.web;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.styzf.core.common.base.PageDTO;
import com.styzf.core.common.constant.CommonConstant;
import com.styzf.core.common.util.IdWorker;
import com.styzf.core.redis.RedisUtil;
import com.styzf.sso.aspect.validated.annotation.RegisterValidated;
import com.styzf.sso.constant.SSOConstant;
import com.styzf.sso.dto.UserDto;
import com.styzf.sso.dto.UserInfo;
import com.styzf.sso.service.IStyzfUserService;
import com.styzf.sso.util.CookieUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户主表 前端控制器
 * </p>
 *
 * @author styzf
 * @since 2018-08-14
 */
@Api(value = "用户操作")
@RestController
@RequestMapping(value = "/sso/styzfUser", method = {RequestMethod.GET, RequestMethod.POST})
public class StyzfUserController {

    @Autowired
    private IStyzfUserService styzfUserService;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @ApiOperation(value = "注册register", notes = "注册register")
    @RegisterValidated
    @RequestMapping("register")
    public Boolean register(UserDto userDto) {
        Date today = new Date();
        userDto.setStRole(CommonConstant.ROLE.VIP0);
        userDto.setStyzfCreatorId(CommonConstant.ROLE.ADMIN);
        userDto.setStyzfCreateTime(today);
        userDto.setStyzfLastUpdateId(CommonConstant.ROLE.ADMIN);
        userDto.setStyzfLastUpdateTime(today);
        Boolean register = styzfUserService.register(userDto);
        if (register) {
            return register;
        }
        
        return register;
    }
    
    @ApiOperation(value = "登陆login", notes = "登陆login")
    @RegisterValidated
    @RequestMapping("login")
    public Boolean login(UserDto userDto, PageDTO<UserDto> page) {
        PageDTO<UserDto> baseSelectPage = styzfUserService.baseSelectPage(page);
        return Boolean.FALSE;
    }
    
    @ApiOperation(value = "验证是否登录：匿名登录和未登录返回false", notes = "验证是否登录")
    @RegisterValidated
    @RequestMapping("checkLogin")
    public Boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.getCookie(request, SSOConstant.Common.TOKEN);
        String token = null;
        UserInfo userInfo = null;
        if (cookie != null) {
            token = cookie.getValue();
            userInfo = redisUtil.getObject(token, UserInfo.class);
        }
        if (userInfo == null) {
            /** 匿名用户登录 */
            String idWorker = IdWorker.getIdStr();
            CookieUtils.setCookie(request, response, SSOConstant.Common.TOKEN, 
                    idWorker, 60 * 60 * 12, Boolean.FALSE);
            userInfo = new UserInfo();
            String substring = idWorker.substring(idWorker.length() - 8, idWorker.length());
            userInfo.setStName(SSOConstant.Common.ANONYMOUS_USER + substring);
            redisUtil.setObject(idWorker, userInfo);
            return Boolean.FALSE;
        }
        CookieUtils.setCookie(request, response, SSOConstant.Common.TOKEN, 
                token, 60 * 60 * 12, Boolean.FALSE);
        redisUtil.setObject(token, userInfo);
        if (userInfo.getStName().startsWith(SSOConstant.Common.ANONYMOUS_USER)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}

