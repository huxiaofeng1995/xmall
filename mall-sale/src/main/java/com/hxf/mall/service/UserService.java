package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;

public interface UserService {
    public T_MALL_USER_ACCOUNT getUserByUserName(T_MALL_USER_ACCOUNT user);

    void registry(T_MALL_USER_ACCOUNT user);
}
