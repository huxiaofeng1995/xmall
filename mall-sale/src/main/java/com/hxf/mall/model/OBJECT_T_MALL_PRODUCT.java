package com.hxf.mall.model;

import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_COLOR;
import com.hxf.mall.bean.T_MALL_PRODUCT_VERSION;

import java.util.List;

public class OBJECT_T_MALL_PRODUCT extends T_MALL_PRODUCT {
    private List<T_MALL_PRODUCT_COLOR> list_color ;

    private List<T_MALL_PRODUCT_VERSION> list_version;

    public List<T_MALL_PRODUCT_COLOR> getList_color() {
        return list_color;
    }

    public void setList_color(List<T_MALL_PRODUCT_COLOR> list_color) {
        this.list_color = list_color;
    }

    public List<T_MALL_PRODUCT_VERSION> getList_version() {
        return list_version;
    }

    public void setList_version(List<T_MALL_PRODUCT_VERSION> list_version) {
        this.list_version = list_version;
    }
}
