package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

public interface UserService {
    public T_MALL_USER_ACCOUNT getUserByUserName(T_MALL_USER_ACCOUNT user);

    void registry(T_MALL_USER_ACCOUNT user);

    List<T_MALL_ADDRESS> get_address_list(T_MALL_USER_ACCOUNT user);

    void add_address(T_MALL_ADDRESS address);
}
