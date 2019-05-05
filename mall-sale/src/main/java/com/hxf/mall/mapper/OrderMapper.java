package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_ORDER;
import com.hxf.mall.bean.T_MALL_ORDER_INFO;
import com.hxf.mall.model.OBJECT_T_MALL_FLOW;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;

import java.util.Map;

public interface OrderMapper {
    void insert_order(OBJECT_T_MALL_ORDER order);

    void insert_flow(OBJECT_T_MALL_FLOW flow);

    void insert_infos(Map<Object, Object> map_info);

    T_MALL_ORDER select_order(Integer id);

    void update_order(OBJECT_T_MALL_ORDER order);

    void update_flow(OBJECT_T_MALL_FLOW flow);

    int select_count_kc(Integer sku_id);

    long select_kc(Map<Object, Object> map);

    void update_kc(T_MALL_ORDER_INFO info);
}
