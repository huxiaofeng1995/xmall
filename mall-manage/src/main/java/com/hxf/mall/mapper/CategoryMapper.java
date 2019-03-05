package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;

import java.util.List;

public interface CategoryMapper {
    List<T_MALL_CLASS_1> list_class1();

    List<T_MALL_CLASS_2> list_class2(int flbh1);

    List<T_MALL_TRADE_MARK> list_tm(int flbh1);
}
