package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;

public interface CartMapper {
    T_MALL_SHOPPINGCAR select_cart(Integer id);
}
