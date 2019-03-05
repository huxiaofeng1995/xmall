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
}
