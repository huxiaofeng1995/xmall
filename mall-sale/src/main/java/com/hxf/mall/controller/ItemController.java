package com.hxf.mall.controller;

import com.hxf.mall.model.DETAIL_T_MALL_SKU;
import com.hxf.mall.model.OBJECT_T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_SKU_INFO;
import com.hxf.mall.bean.T_MALL_SKU;
import com.hxf.mall.service.ItemService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/sku-detail/{id}")
    public Result goto_sku_detail(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<>();
        DETAIL_T_MALL_SKU detail_sku = itemService.get_sku_detail(id);
        Integer spu_id = detail_sku.getSpu().getId();
        OBJECT_T_MALL_PRODUCT obj_spu = itemService.get_spu_sale_attr(spu_id);//获取商品销售属性
        map.put("detail_sku",detail_sku);
        map.put("sale_attr",obj_spu);
        return Result.success(map);
    }


    @GetMapping("sku/cid/{cid}/vid/{vid}")
    public Result getSkuByColorAndVersion(@PathVariable Integer cid, @PathVariable Integer vid){
        Map<String,Object> map = new HashMap<>();
        T_MALL_PRODUCT_SKU_INFO item = itemService.get_sku_id(cid,vid);
        if(item != null){
            DETAIL_T_MALL_SKU detail_sku = itemService.get_sku_detail(item.getSku_id());
            map.put("detail_sku", detail_sku);
            map.put("isSkuExist", 1);
        }else {
            map.put("isSkuExist", 0);
        }
        return Result.success(map);
    }
}
