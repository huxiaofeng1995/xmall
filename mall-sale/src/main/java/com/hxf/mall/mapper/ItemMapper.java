package com.hxf.mall.mapper;

import com.hxf.mall.bean.*;

import java.util.List;
import java.util.Map;

public interface ItemMapper {

    public DETAIL_T_MALL_SKU select_detail_sku(int sku_id);

    public List<T_MALL_SKU> select_list_sku_by_spu(int spu_id);

    List<T_MALL_PRODUCT_COLOR> select_color_list(int spu_id);

    List<T_MALL_PRODUCT_VERSION> select_version_list(int spu_id);

    T_MALL_PRODUCT_SKU_INFO select_skuId(Map<String, Integer> map);
}
