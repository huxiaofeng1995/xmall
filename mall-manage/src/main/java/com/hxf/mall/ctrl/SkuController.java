package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_SKU_INFO;
import com.hxf.mall.bean.T_MALL_SKU;
import com.hxf.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.hxf.mall.service.SkuService;
import com.hxf.mall.to.MODEL_T_MALL_SKU;
import com.hxf.mall.to.ResponseData;
import com.hxf.mall.util.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class SkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping("sku")
    public ResponseData addSku(Integer shp_id, Integer pp_id, String sku_info){
        List<MODEL_T_MALL_SKU> skuList = JSON.parseArray(sku_info, MODEL_T_MALL_SKU.class);
        for(MODEL_T_MALL_SKU sku : skuList){
            //t_mall_sku表
            sku.setShp_id(shp_id);
            skuService.addSku(sku);

            //t_mall_product_sku_info表
            Integer colorId = Integer.parseInt(sku.getShp_ys().split("-")[0]);
            Integer versionId = Integer.parseInt(sku.getShp_bb().split("-")[0]);
            T_MALL_PRODUCT_SKU_INFO skuInfo = new T_MALL_PRODUCT_SKU_INFO(sku.getId(),pp_id,colorId,versionId);
            skuService.addSkuInfo(skuInfo);

            //t_mall_sku_attr_value表
            List<String> list_value = sku.getList_value();
            for(String value : list_value){
                if(value != null && !value.equals("")) {
                    Integer shxzh_id = Integer.parseInt(value.split("-")[0]);
                    Integer shxm_id = Integer.parseInt(value.split("-")[1]);
                    T_MALL_SKU_ATTR_VALUE sku_attr_value = new T_MALL_SKU_ATTR_VALUE(shxm_id, shxzh_id, shp_id, sku.getId());
                    skuService.addSkuAttrValue(sku_attr_value);
                }
            }
        }
        return ResponseDataUtil.buildSuccess("success");
    }

}
