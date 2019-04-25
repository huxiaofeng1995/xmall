package com.hxf.mall.service.impl;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.mapper.UserMapper;
import com.hxf.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public T_MALL_USER_ACCOUNT getUserByUserName(T_MALL_USER_ACCOUNT user) {
        return userMapper.select_user_by_yhmch(user);
    }

    @Override
    public void registry(T_MALL_USER_ACCOUNT user) {
        userMapper.insert_user(user);
    }

    @Override
    public List<T_MALL_ADDRESS> get_address_list(T_MALL_USER_ACCOUNT user) {
        return userMapper.select_address(user);
    }

    @Override
    public void add_address(T_MALL_ADDRESS address) {
        userMapper.insert_address(address);
    }
}
