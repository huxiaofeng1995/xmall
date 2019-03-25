package com.hxf.mall.service;

import com.hxf.mall.mapper.AttrMapper;
import com.hxf.mall.to.OBJECT_T_MALL_ATTR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrService {

    @Autowired
    private AttrMapper attrMapper;

    public List<OBJECT_T_MALL_ATTR> get_attr_list(Integer flbh2){
        return attrMapper.select_attr_value_list(flbh2);
    }

}
