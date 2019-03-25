package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_VALUE;
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

    public void addAttrValue(T_MALL_VALUE value) {
        attrMapper.insert_attr_value(value);
    }

    public void deleteAttr(Integer id) {
        attrMapper.delete_attr(id);
        attrMapper.delete_attr_value_by_shxmid(id);
    }

    public void deleteAttrValue(Integer id) {
        attrMapper.delete_attr_value(id);
    }

    public void addAttr(OBJECT_T_MALL_ATTR t_mall_attr) {
        attrMapper.insert_attr(t_mall_attr);
        attrMapper.insert_attr_values(t_mall_attr.getId(), t_mall_attr.getList_value());
    }
}
