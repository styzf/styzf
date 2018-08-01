package com.styzf.test.service;

import java.util.List;

import com.styzf.test.dto.UserDto;

/**
 * 只读测试
 * @author styzf
 * @date 2018年7月24日 
 *
 */
public interface IUserReadOnlyService {

    public boolean deleteAll();

    public List<UserDto> selectListBySQL();
    
    public Boolean insert(UserDto dto);
}
