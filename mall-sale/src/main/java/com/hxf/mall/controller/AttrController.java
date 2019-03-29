package com.hxf.mall.controller;

import com.hxf.mall.model.OBJECT_T_MALL_ATTR;
import com.hxf.mall.service.AttrService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttrController {

    @Autowired
    private AttrService attrService;

    @GetMapping("attr-list/{flbh2}")
    public Result getAttrList(@PathVariable Integer flbh2){
        return Result.success(attrService.get_attr_list(flbh2));
    }
}
