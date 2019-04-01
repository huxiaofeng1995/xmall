package com.hxf.mall.service;

import com.hxf.mall.bean.OBJECT_T_MALL_SKU;
import com.hxf.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.hxf.mall.bean.T_MALL_VALUE;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ListService {

    public List<OBJECT_T_MALL_SKU> get_sku_list(int flbh2);

    List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(List<T_MALL_VALUE> list_value, int flbh2);
}
