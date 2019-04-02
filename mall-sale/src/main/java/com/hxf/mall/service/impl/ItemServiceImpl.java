package com.hxf.mall.service.impl;

import com.hxf.mall.bean.*;
import com.hxf.mall.mapper.ItemMapper;
import com.hxf.mall.model.DETAIL_T_MALL_SKU;
import com.hxf.mall.model.OBJECT_T_MALL_PRODUCT;
import com.hxf.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<T_MALL_SKU> get_list_sku_by_spu(int spu_id) {
        return itemMapper.select_list_sku_by_spu(spu_id);
    }

    @Override
    public DETAIL_T_MALL_SKU get_sku_detail(int sku_id) {

        return itemMapper.select_detail_sku(sku_id);
    }

    @Override
    public OBJECT_T_MALL_PRODUCT get_spu_sale_attr(int spu_id) {
        List<T_MALL_PRODUCT_COLOR> list_color = itemMapper.select_color_list(spu_id);
        List<T_MALL_PRODUCT_VERSION> list_version = itemMapper.select_version_list(spu_id);
        OBJECT_T_MALL_PRODUCT spu = new OBJECT_T_MALL_PRODUCT();
        spu.setList_color(list_color);
        spu.setList_version(list_version);
        return spu;
    }

    @Override
    public T_MALL_PRODUCT_SKU_INFO get_sku_id(int color, int version) {
        Map<String,Integer> map = new HashMap<>();
        map.put("color", color);
        map.put("version", version);
        return itemMapper.select_skuId(map);
    }
}
