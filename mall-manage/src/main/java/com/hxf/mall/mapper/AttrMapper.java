package com.hxf.mall.mapper;

import com.hxf.mall.to.OBJECT_T_MALL_ATTR;

import java.util.List;

public interface AttrMapper {
    public List<OBJECT_T_MALL_ATTR> select_attr_value_list(Integer flbh2);
}
