package com.hxf.mall.mapper;

import com.hxf.mall.model.OBJECT_T_MALL_SKU;

import java.util.List;
import java.util.Map;

public interface ListMapper {
    public List<OBJECT_T_MALL_SKU> select_list_sku(int flbh2);

    List<OBJECT_T_MALL_SKU> select_list_sku_by_attr(Map<String, Object> map);
}
