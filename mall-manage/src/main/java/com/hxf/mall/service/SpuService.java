package com.hxf.mall.service;

import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_IMAGE;
import com.hxf.mall.mapper.SpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpuService {

    @Autowired
    private SpuMapper spuMapper;

    public void sava_spu(List<String> imgs, T_MALL_PRODUCT spu) {
        String img = imgs.get(0);//取第一张图片作为主图片
        spu.setShp_tp(img);
        spuMapper.insert_spu(spu);
//        方式1
//        for(String image : imgs){
//            T_MALL_PRODUCT_IMAGE productImage = new T_MALL_PRODUCT_IMAGE();
//            productImage.setShp_id(spu.getId());
//            productImage.setUrl(img);
//            spuMapper.insert_spu_image(productImage);
//        }

        //方式2
        //根据主键，批量插入spu图片
        Map<String, Object> map = new HashMap<>();
        map.put("shp_id", spu.getId());
        map.put("imgs", imgs);
        //spuMapper.insert_imgs(spu.getId(), imgs);
        //遇到多参数传递时，最好封装成map，这样方便在mapper.xml中获取
        spuMapper.insert_imgs(map);
    }

    public List<T_MALL_PRODUCT> getSpuList(T_MALL_PRODUCT spu) {
        return spuMapper.select_spu_list(spu);
    }
}
