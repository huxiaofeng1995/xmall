package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

public interface UserMapper {

    T_MALL_USER_ACCOUNT select_user_by_yhmch(T_MALL_USER_ACCOUNT user);

    void insert_user(T_MALL_USER_ACCOUNT user);

    List<T_MALL_ADDRESS> select_address(T_MALL_USER_ACCOUNT user);

    void insert_address(T_MALL_ADDRESS address);
}
