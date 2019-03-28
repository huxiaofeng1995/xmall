package com.hxf.mall.service.impl;

import com.hxf.mall.bean.MODEL_T_MALL_ATTR;
import com.hxf.mall.bean.OBJECT_T_MALL_ATTR;
import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_VALUE;
import com.hxf.mall.mapper.AttrMapper;

import com.hxf.mall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;


    @Override
    public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
        return attrMapper.select_attr_list(flbh2);
    }
}
