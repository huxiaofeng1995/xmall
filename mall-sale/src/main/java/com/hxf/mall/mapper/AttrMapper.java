package com.hxf.mall.mapper;

import com.hxf.mall.bean.OBJECT_T_MALL_ATTR;
import com.hxf.mall.bean.T_MALL_ATTR;
import com.hxf.mall.bean.T_MALL_VALUE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttrMapper {

    public List<OBJECT_T_MALL_ATTR> select_attr_list(int flbh2);
}
