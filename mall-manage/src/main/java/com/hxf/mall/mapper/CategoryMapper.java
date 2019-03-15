package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    List<T_MALL_CLASS_1> list_class1();

    List<T_MALL_CLASS_2> list_class2(int flbh1);

    List<T_MALL_TRADE_MARK> list_tm(int flbh1);

    void insert_class1(T_MALL_CLASS_1 class1);

    void update_class1(T_MALL_CLASS_1 class1);

    void delete_class1(int id);

    void insert_class2(T_MALL_CLASS_2 class2);

    void update_class2(T_MALL_CLASS_2 class2);

    void delete_class2(int id);

    void insert_tm(T_MALL_TRADE_MARK tm);

    void insert_tm_class(Map<String, Object> map);

    void update_tm(T_MALL_TRADE_MARK tm);

    void delete_tm(int id);

    void delete_tm_class(int pp_id);

    List<T_MALL_TRADE_MARK> list_trademark();

    void delete_tm_class2(Map<String, Object> map);
}
