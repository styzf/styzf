package com.styzf.test.service;

import java.util.List;

import com.styzf.test.dto.UserDto;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService {

	public boolean deleteAll();

	public List<UserDto> selectListBySQL();
	
	public Boolean insert(UserDto dto);
}