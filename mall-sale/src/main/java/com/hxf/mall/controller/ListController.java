package com.hxf.mall.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxf.mall.bean.T_MALL_VALUE;
import com.hxf.mall.model.OBJECT_T_MALL_SKU;
import com.hxf.mall.service.ListService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping("sku-list")
    public Result get_list_by_attr(String list_value, int flbh2,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "8")int pageSize,String sort){
        if(sort != null && !sort.equals("")) {
            if(sort.equals("all") || sort.equals("review")){
                sort = "jg";
            }else if(sort.equals("chjshj")){
                sort = "unix_timestamp(b.chjshj) desc";//因为查询结果集chjshj字段有多个，用 b代表了mapper文件里改查询的sku表
            }
            PageHelper.startPage(pageNum, pageSize,sort);
        }else {
            PageHelper.startPage(pageNum, pageSize);
        }
        if(list_value != null && !list_value.equals("[]")) {
            List<T_MALL_VALUE> values = JSON.parseArray(list_value, T_MALL_VALUE.class);
            List<OBJECT_T_MALL_SKU> list = listService.get_sku_list_by_attr(values,flbh2);
            PageInfo<OBJECT_T_MALL_SKU> page = new PageInfo<>(list);
            return Result.success(page);
        }else{
            PageInfo<OBJECT_T_MALL_SKU> page = new PageInfo<>(listService.get_sku_list(flbh2));
            return Result.success(page);
        }

    }
}
