package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;

public interface OrderService {
    void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address);
}
