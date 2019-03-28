package com.hxf.mall.to;

import com.hxf.mall.bean.T_MALL_SKU;

import java.util.List;

public class MODEL_T_MALL_SKU extends T_MALL_SKU {

    private String shp_ys;

    private String shp_bb;

    private List<String> list_value;

    public String getShp_ys() {
        return shp_ys;
    }

    public void setShp_ys(String shp_ys) {
        this.shp_ys = shp_ys;
    }

    public String getShp_bb() {
        return shp_bb;
    }

    public void setShp_bb(String shp_bb) {
        this.shp_bb = shp_bb;
    }

    public List<String> getList_value() {
        return list_value;
    }

    public void setList_value(List<String> list_value) {
        this.list_value = list_value;
    }
}
