package com.styzf.sso.service.impl;

import org.springframework.stereotype.Service;

import com.styzf.core.common.util.OrikaUtil;
import com.styzf.springboot.mybatisPlus.service.impl.BaseServiceImpl;
import com.styzf.sso.dto.UserDto;
import com.styzf.sso.entity.StyzfUser;
import com.styzf.sso.mapper.StyzfUserMapper;
import com.styzf.sso.service.IStyzfUserService;

/**
 * <p>
 * 用户主表 服务实现类
 * </p>
 *
 * @author styzf
 * @since 2018-08-14
 */
@Service("styzfUserService")
public class StyzfUserServiceImpl extends BaseServiceImpl<StyzfUser, UserDto, StyzfUserMapper> implements IStyzfUserService {
    
    public Boolean register(UserDto userDto) {
        return insert(OrikaUtil.map(userDto, StyzfUser.class));
    }
}
