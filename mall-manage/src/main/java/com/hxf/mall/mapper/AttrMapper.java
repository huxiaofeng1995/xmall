package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_VALUE;
import com.hxf.mall.to.OBJECT_T_MALL_ATTR;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttrMapper {
    public List<OBJECT_T_MALL_ATTR> select_attr_value_list(Integer flbh2);

    void insert_attr_value(T_MALL_VALUE value);

    void delete_attr(Integer id);

    void delete_attr_value(Integer id);

    void delete_attr_value_by_shxmid(Integer id);

    public void insert_attr_values(@Param("shxid") int shxid, @Param("list_value")List<T_MALL_VALUE> list);

    void insert_attr(OBJECT_T_MALL_ATTR t_mall_attr);
}
