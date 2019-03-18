package com.hxf.mall.mapper;

import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_IMAGE;

import java.util.List;
import java.util.Map;

public interface SpuMapper {
    void insert_spu(T_MALL_PRODUCT spu);

    void insert_spu_image(T_MALL_PRODUCT_IMAGE productImage);

    void insert_imgs(Map<String, Object> map);

    List<T_MALL_PRODUCT> select_spu_list(T_MALL_PRODUCT t_mall_product);
}
