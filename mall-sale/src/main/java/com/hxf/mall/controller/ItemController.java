package com.hxf.mall.controller;

import com.hxf.mall.bean.DETAIL_T_MALL_SKU;
import com.hxf.mall.bean.OBJECT_T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_SKU_INFO;
import com.hxf.mall.bean.T_MALL_SKU;
import com.hxf.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/goto_sku_detail")
    public String goto_sku_detail(int sku_id, int spu_id, Map map){
        DETAIL_T_MALL_SKU detail_sku = itemService.get_sku_detail(sku_id);
        List<T_MALL_SKU> list_sku = itemService.get_list_sku_by_spu(spu_id);
        OBJECT_T_MALL_PRODUCT obj_spu = itemService.get_spu_sale_attr(spu_id);
        map.put("detail_sku",detail_sku);
        map.put("list_sku",list_sku);
        map.put("obj_spu",obj_spu);
        return "skuDetail";
    }
    @RequestMapping("get_skuId")
    @ResponseBody
    public int get_sku_id(int color_id, int version_id){
        T_MALL_PRODUCT_SKU_INFO item = itemService.get_sku_id(color_id,version_id);
        if(item != null){
            return item.getSku_id();
        }
        return -1;
    }
}
