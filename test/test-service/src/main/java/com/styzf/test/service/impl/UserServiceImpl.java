package com.styzf.test.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.annotations.DataSource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.styzf.test.dto.UserDto;
import com.styzf.test.entity.User;
import com.styzf.test.mapper.UserMapper;
import com.styzf.test.service.IUserService;

/**
 * User 表数据服务层接口实现类
 * @author styzf
 * @date 2018年7月23日 
 *
 */
@DataSource(name = "styzf-write")
@Service("userService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService ,IService<User> {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<UserDto> selectListBySQL() {
//	    List<User> userList = selectList(null);
	    Wrapper<User> wrapper = new EntityWrapper<>();
	    List<User> userList = baseMapper.selectListBySQL();
	    List<UserDto> dtoList = userList.parallelStream().map(user -> {
	        UserDto dto = new UserDto();
	        BeanUtils.copyProperties(user, dto);
	        return dto;
	    }).collect(Collectors.toList());
	    return dtoList;
	}

	@Override
	public Boolean insert(UserDto dto) {
	    Page<User> page = new Page<>();
	    User user = new User();
	    BeanUtils.copyProperties(dto, user);
	    user.setTestDate(new Date());
	    insert(user);
	    return Boolean.TRUE;
    }
}