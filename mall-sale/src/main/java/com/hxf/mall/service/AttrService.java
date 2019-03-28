package com.hxf.mall.service;

import com.hxf.mall.bean.MODEL_T_MALL_ATTR;
import com.hxf.mall.bean.OBJECT_T_MALL_ATTR;
import com.hxf.mall.bean.T_MALL_PRODUCT;

import java.util.List;

public interface AttrService {

    public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);
}
