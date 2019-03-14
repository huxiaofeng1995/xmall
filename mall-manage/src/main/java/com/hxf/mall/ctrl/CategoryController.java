package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.service.CategoryService;
import com.hxf.mall.to.AMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("listcategory1")
    public List<T_MALL_CLASS_1> list_cate1(){
        return categoryService.getfl1List();
    }

    @GetMapping("listcategory2/{flbh1}")
    public List<T_MALL_CLASS_2> list_cate2(@PathVariable int flbh1){
        return categoryService.getfl2List(flbh1);
    }

    @GetMapping("listtrademark/{flbh1}")
    public List<T_MALL_TRADE_MARK> list_trademark(@PathVariable int flbh1){
        return categoryService.gettmList(flbh1);
    }

    @GetMapping("category/first")
    public AMessage list_first_cate(){
        AMessage aMessage = new AMessage();
        aMessage.setData(categoryService.getfl1List());
        return aMessage;
    }

    @PostMapping("category/first")
    public AMessage add_first_cate(T_MALL_CLASS_1 class1){
        categoryService.add_first_cate(class1);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @PutMapping("category/first")
    public AMessage update_first_cate(@RequestBody String info){
        T_MALL_CLASS_1 class1 = JSON.parseObject(info, T_MALL_CLASS_1.class);
        categoryService.update_first_cate(class1);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @DeleteMapping("category/first/{id}")
    public AMessage delete_first_cate(@PathVariable int id){
        categoryService.delete_first_cate(id);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @GetMapping("category/second/{flbh1}")
    public List<T_MALL_CLASS_2> list_second_cate(@PathVariable int flbh1){
        return categoryService.getfl2List(flbh1);
    }

    @GetMapping("category/trademark/{flbh1}")
    public List<T_MALL_TRADE_MARK> listtrademark(@PathVariable int flbh1){
        return categoryService.gettmList(flbh1);
    }
}
