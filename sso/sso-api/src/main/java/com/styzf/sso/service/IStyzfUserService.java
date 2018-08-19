package com.styzf.sso.service;

import com.styzf.core.common.base.BaseService;
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
public interface IStyzfUserService extends BaseService<UserDto>{
    public Boolean register(UserDto userDto);
}
