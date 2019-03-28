package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_PRODUCT_SKU_INFO;
import com.hxf.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.hxf.mall.mapper.SkuMapper;
import com.hxf.mall.to.MODEL_T_MALL_SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuService {

    @Autowired
    private SkuMapper skuMapper;

    public void addSku(MODEL_T_MALL_SKU sku) {
        skuMapper.insert_sku(sku);
    }

    public void addSkuInfo(T_MALL_PRODUCT_SKU_INFO skuInfo) {
        skuMapper.insert_sku_info(skuInfo);
    }

    public void addSkuAttrValue(T_MALL_SKU_ATTR_VALUE sku_attr_value) {
        skuMapper.insert_sku_attr_value(sku_attr_value);
    }
}
