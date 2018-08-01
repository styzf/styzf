package com.styzf.test.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.styzf.springboot.mybatisPlus.dataSource.DataSource;
import com.styzf.test.dto.UserDto;
import com.styzf.test.entity.User;
import com.styzf.test.mapper.UserMapper;
import com.styzf.test.service.IUserReadOnlyService;

@DataSource(readOnly = true)
@Service("userReadOnlyService")
@Transactional
public class UserServiceReadOnlyImpl extends ServiceImpl<UserMapper, User> implements IUserReadOnlyService ,IService<User>  {

    @Override
    public boolean deleteAll() {
        return retBool(baseMapper.deleteAll());
    }

    @Override
    public List<UserDto> selectListBySQL() {
//      List<User> userList = selectList(null);
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
