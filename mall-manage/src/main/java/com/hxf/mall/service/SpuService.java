package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_IMAGE;
import com.hxf.mall.mapper.SpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuService {

    @Autowired
    private SpuMapper spuMapper;

    public void sava_spu(List<String> imgs, T_MALL_PRODUCT spu) {
        String img = imgs.get(0);//取第一张图片作为主图片
        spu.setShp_tp(img);
        spuMapper.insert_spu(spu);
        for(String image : imgs){
            T_MALL_PRODUCT_IMAGE productImage = new T_MALL_PRODUCT_IMAGE();
            productImage.setShp_id(spu.getId());
            productImage.setUrl(img);
            spuMapper.insert_spu_image(productImage);
        }

    }
}
