package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
