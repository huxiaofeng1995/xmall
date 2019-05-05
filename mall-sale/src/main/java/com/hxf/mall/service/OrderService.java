package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_ORDER;
import com.hxf.mall.exception.OverSaleException;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;

public interface OrderService {
    void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address);

    T_MALL_ORDER select_order(Integer id);

    void pay(OBJECT_T_MALL_ORDER order) throws OverSaleException;
}
