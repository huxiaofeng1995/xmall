package com.hxf.mall.service.impl;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.mapper.CategoryMapper;
import com.hxf.mall.model.MODEL_T_MALL_CLASS1;
import com.hxf.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<T_MALL_CLASS_1> getfl1List() {
        return categoryMapper.list_class1();
    }

    @Override
    public List<MODEL_T_MALL_CLASS1> getCategory() {
        return categoryMapper.list_cate();
    }
}
