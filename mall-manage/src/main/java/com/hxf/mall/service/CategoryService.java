package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<T_MALL_CLASS_1> getfl1List() {
        return categoryMapper.list_class1();
    }

    public List<T_MALL_CLASS_2> getfl2List(int flbh1) {
        return categoryMapper.list_class2(flbh1);
    }

    public List<T_MALL_TRADE_MARK> gettmList(int flbh1) {
        return categoryMapper.list_tm(flbh1);
    }

    public void add_first_cate(T_MALL_CLASS_1 class1) {
        categoryMapper.insert_class1(class1);
    }

    public void update_first_cate(T_MALL_CLASS_1 class1) {
        categoryMapper.update_class1(class1);
    }

    public void delete_first_cate(int id) {
        categoryMapper.delete_class1(id);
    }

    public void add_second_cate(T_MALL_CLASS_2 class2) {
        categoryMapper.insert_class2(class2);
    }

    public void update_second_cate(T_MALL_CLASS_2 class2) {
        categoryMapper.update_class2(class2);
    }
    public void delete_second_cate(int id) {
        categoryMapper.delete_class2(id);
    }

    public void add_tm(T_MALL_TRADE_MARK tm) {
        categoryMapper.insert_tm(tm);
    }

    public void add_tm_class(Map<String, Object> map) {
        categoryMapper.insert_tm_class(map);
    }

    public void update_tm(T_MALL_TRADE_MARK tm) {
        categoryMapper.update_tm(tm);
    }

    public void delete_tm(int id) {
        categoryMapper.delete_tm(id);
    }

    public void delete_tm_class(int pp_id) {
        categoryMapper.delete_tm_class(pp_id);
    }

    public List<T_MALL_TRADE_MARK> gettmList(){
        return categoryMapper.list_trademark();
    }

    public void delete_tm_class2(Map<String, Object> map) {
        categoryMapper.delete_tm_class2(map);
    }
}
