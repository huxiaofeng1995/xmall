package com.hxf.mall.ctrl;

import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
