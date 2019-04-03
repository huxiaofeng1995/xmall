package com.hxf.mall.service.impl;

import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.mapper.CartMapper;
import com.hxf.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;

    @Override
    public T_MALL_SHOPPINGCAR getCart(Integer id) {
        return cartMapper.select_cart(id);
    }
}
