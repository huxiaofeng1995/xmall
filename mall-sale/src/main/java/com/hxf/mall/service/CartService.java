package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.model.OBJECT_PRODUCT_SKU_INFO;

import java.util.List;

public interface CartService {

    public T_MALL_SHOPPINGCAR getCart(Integer id);

    List<T_MALL_SHOPPINGCAR> get_cart_list_by_user(T_MALL_USER_ACCOUNT user);

    boolean if_cart_exists(T_MALL_SHOPPINGCAR cart);

    void add_cart(T_MALL_SHOPPINGCAR cart);

    void update_cart(T_MALL_SHOPPINGCAR cart_db);

    OBJECT_PRODUCT_SKU_INFO getSaleAttrBySkuId(Integer sku_id);

    void delete_cart(T_MALL_SHOPPINGCAR cart);
}
