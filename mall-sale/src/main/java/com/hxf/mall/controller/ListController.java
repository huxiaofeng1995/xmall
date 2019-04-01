package com.hxf.mall.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxf.mall.bean.OBJECT_T_MALL_SKU;
import com.hxf.mall.bean.T_MALL_VALUE;
import com.hxf.mall.service.ListService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping("sku-list")
    public Result get_list_by_attr(String list_value, int flbh2){
        if(list_value != null && !list_value.equals("[]")) {
            List<T_MALL_VALUE> values = JSON.parseArray(list_value, T_MALL_VALUE.class);
            return Result.success(listService.get_sku_list_by_attr(values,flbh2));
        }else{
            return Result.success(listService.get_sku_list(flbh2));
        }

    }
}
