package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.model.OBJECT_PRODUCT_SKU_INFO;

import java.util.List;
import java.util.Map;

public interface CartMapper {
    T_MALL_SHOPPINGCAR select_cart(Integer id);

    List<T_MALL_SHOPPINGCAR> select_cart_by_uid(T_MALL_USER_ACCOUNT user);

    T_MALL_SHOPPINGCAR select_cart_by_skuId(T_MALL_SHOPPINGCAR cart);
    
    void insert_cart(T_MALL_SHOPPINGCAR cart);

    void update_cart(T_MALL_SHOPPINGCAR cart);

    OBJECT_PRODUCT_SKU_INFO select_sale_attr_by_skuId(Integer sku_id);

    void delete_cart(T_MALL_SHOPPINGCAR cart);

    void delete_carts(Map<Object, Object> map);
}
