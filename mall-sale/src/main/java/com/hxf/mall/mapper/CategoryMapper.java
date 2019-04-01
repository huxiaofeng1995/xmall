package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.model.MODEL_T_MALL_CLASS1;

import java.util.List;


public interface CategoryMapper {
    List<T_MALL_CLASS_1> list_class1();

    List<MODEL_T_MALL_CLASS1> list_cate();
}
