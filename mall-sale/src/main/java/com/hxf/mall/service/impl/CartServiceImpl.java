package com.hxf.mall.service.impl;

import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.mapper.CartMapper;
import com.hxf.mall.model.OBJECT_PRODUCT_SKU_INFO;
import com.hxf.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;

    @Override
    public T_MALL_SHOPPINGCAR getCart(Integer id) {
        return cartMapper.select_cart(id);
    }

    @Override
    public List<T_MALL_SHOPPINGCAR> get_cart_list_by_user(T_MALL_USER_ACCOUNT user) {
        return cartMapper.select_cart_by_uid(user);
    }

    @Override
    public boolean if_cart_exists(T_MALL_SHOPPINGCAR cart) {
        T_MALL_SHOPPINGCAR ca = cartMapper.select_cart_by_skuId(cart);
        if(ca != null){
            return true;
        }
        return false;
    }

    @Override
    public void add_cart(T_MALL_SHOPPINGCAR cart) {
        cartMapper.insert_cart(cart);
    }

    @Override
    public void update_cart(T_MALL_SHOPPINGCAR cart) {
        cartMapper.update_cart(cart);
    }

    @Override
    public OBJECT_PRODUCT_SKU_INFO getSaleAttrBySkuId(Integer sku_id) {
        return cartMapper.select_sale_attr_by_skuId(sku_id);
    }

    @Override
    public void delete_cart(T_MALL_SHOPPINGCAR cart) {
        cartMapper.delete_cart(cart);
    }
}
