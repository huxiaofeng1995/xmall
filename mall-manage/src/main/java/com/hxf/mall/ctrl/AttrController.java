package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxf.mall.bean.T_MALL_PRODUCT_COLOR;
import com.hxf.mall.bean.T_MALL_PRODUCT_VERSION;
import com.hxf.mall.bean.T_MALL_VALUE;
import com.hxf.mall.service.AttrService;
import com.hxf.mall.to.OBJECT_T_MALL_ATTR;
import com.hxf.mall.to.ResponseData;
import com.hxf.mall.util.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttrController {

    @Autowired
    private AttrService attrService;

    @GetMapping("attr-list")
    public ResponseData getAttrList(Integer flbh2, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OBJECT_T_MALL_ATTR> list = attrService.get_attr_list(flbh2);
        PageInfo<OBJECT_T_MALL_ATTR> page = new PageInfo<OBJECT_T_MALL_ATTR>(list);
        return ResponseDataUtil.buildSuccess(page);
    }

    @GetMapping("attr-list/{flbh2}")
    public ResponseData getAllAttrList(@PathVariable Integer flbh2){
        return ResponseDataUtil.buildSuccess(attrService.get_attr_list(flbh2));
    }

    @PostMapping("attr-value")
    public ResponseData addAttrValue(T_MALL_VALUE value){
        attrService.addAttrValue(value);
        return ResponseDataUtil.buildSuccess("Attr Value Add Success");
    }

    @DeleteMapping("attr/{id}")
    public ResponseData deleteAttr(@PathVariable Integer id){
        attrService.deleteAttr(id);
        return ResponseDataUtil.buildSuccess("Attr Delete Success");
    }

    @DeleteMapping("attr-value/{id}")
    public ResponseData deleteAttrValue(@PathVariable Integer id){
        attrService.deleteAttrValue(id);
        return ResponseDataUtil.buildSuccess("Attr Value Delete Success");
    }

    @PostMapping("/attr")
    public ResponseData addAttr(@RequestBody String attr){
        OBJECT_T_MALL_ATTR t_mall_attr = JSON.parseObject(attr, OBJECT_T_MALL_ATTR.class);
        attrService.addAttr(t_mall_attr);
        return ResponseDataUtil.buildSuccess("Attr Add Success");
    }

    @DeleteMapping("color/{id}")
    public ResponseData deleteColor(@PathVariable Integer id){
        attrService.deleteColor(id);
        return ResponseDataUtil.buildSuccess("Color Delete Success");
    }

    @PostMapping("/color")
    public ResponseData addAttr(T_MALL_PRODUCT_COLOR color){
        attrService.addColor(color);
        return ResponseDataUtil.buildSuccess("Color Add Success");
    }

    @DeleteMapping("version/{id}")
    public ResponseData deleteVersion(@PathVariable Integer id){
        attrService.deleteVersion(id);
        return ResponseDataUtil.buildSuccess("Version Delete Success");
    }

    @PostMapping("/version")
    public ResponseData addVersion(T_MALL_PRODUCT_VERSION version){
        attrService.addVersion(version);
        return ResponseDataUtil.buildSuccess("Version Add Success");
    }
}
