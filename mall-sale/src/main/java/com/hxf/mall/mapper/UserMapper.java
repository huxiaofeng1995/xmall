package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;

public interface UserMapper {

    T_MALL_USER_ACCOUNT select_user_by_yhmch(T_MALL_USER_ACCOUNT user);

    void insert_user(T_MALL_USER_ACCOUNT user);
}
