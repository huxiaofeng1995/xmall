package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_PRODUCT_SKU_INFO;
import com.hxf.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.hxf.mall.to.MODEL_T_MALL_SKU;

public interface SkuMapper {
    void insert_sku(MODEL_T_MALL_SKU sku);

    void insert_sku_info(T_MALL_PRODUCT_SKU_INFO skuInfo);

    void insert_sku_attr_value(T_MALL_SKU_ATTR_VALUE sku_attr_value);
}
