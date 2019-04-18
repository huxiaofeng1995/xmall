package com.hxf.mall.service;

import com.hxf.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String getPassword(String username) {
        return userMapper.select_password_by_username(username);
    }

    public int checkUserBanStatus(String username) {
        return 0;
    }

    public String getRole(String username) {
        return "";
    }

    public String getRolePermission(String username) {
        return "";
    }

    public String getPermission(String username) {
        return "";
    }
}
