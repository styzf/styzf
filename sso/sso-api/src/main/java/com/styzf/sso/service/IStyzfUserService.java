package com.styzf.sso.service;

import com.styzf.sso.dto.UserDto;

/**
 * <p>
 * 用户主表 服务类
 * </p>
 *
 * @author styzf
 * @param <T>
 * @since 2018-08-14
 */
public interface IStyzfUserService {
    public Boolean register(UserDto userDto);
}
