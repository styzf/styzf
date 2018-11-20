package com.styzf.sso.web;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.common.util.Assert;
import com.styzf.core.common.util.IdWorker;
import com.styzf.sso.constant.SSOConstant;
import com.styzf.sso.util.CodeUtil;
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
@Api(value = "测试接口")
@RestController
@RequestMapping(value = "/sso/common", method = {RequestMethod.GET, RequestMethod.POST})
public class StyzfSSOCommonController {
    
    @Autowired
    private RedisUtil redisUtil;
    
    private static char[] codes = new char[62];
    
    static {
        int num_A = 'A', num_a = 'a', num = '0';
        for (int i = 0; i < codes.length; i++) {
            if (i < 26) {
                codes[i] = (char) num_A;
                num_A ++;
                continue;
            }
            if (i < 26 * 2) {
                codes[i] = (char) num_a;
                num_a ++;
                continue;
            }
            codes[i] = (char) num;
            num ++;
        }
    }
    
    private Random ran = new Random();
    
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @RequestMapping("code")
    public void securityCode(HttpServletRequest request, HttpServletResponse response) {
        char[] value = new char[4];
        for (int i = 0; i < 4; i++) {
            value[i] = codes[ran.nextInt(62)];
        }
        String securityCode = new String(value);
        Cookie cookie = CookieUtils.getCookie(request, SSOConstant.Common.TOKEN);
        String token = null;
        if (cookie == null) {
            String idWorker = IdWorker.getIdStr();
            CookieUtils.setCookie(request, response, SSOConstant.Common.TOKEN, 
                    idWorker, 60 * 60 * 12, Boolean.FALSE);
            token = idWorker;
        } else {
            token = cookie.getValue();
        }
        if (StringUtils.isBlank(token)) {
            Assert.throwException("com.styzf.sso.web.StyzfSSOCommonController.securityCode");
        }
        redisUtil.set(SSOConstant.Redis.SSO_SECURITY_CODE_PREFIX + token, securityCode, 60 * 5L);
        CodeUtil.codeImage(securityCode, response);
    }
    
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping("getCode")
    public Response getCode(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.getCookie(request, SSOConstant.Common.TOKEN);
        if (cookie == null) {
            Assert.throwException("com.styzf.sso.web.StyzfSSOCommonController.getCode");
        } 
        String token = cookie.getValue();
        String data =redisUtil.get(SSOConstant.Redis.SSO_SECURITY_CODE_PREFIX + token);
        return new SuccessResponseData(data);
    }
    
    
}

