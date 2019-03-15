package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.service.CategoryService;
import com.hxf.mall.to.AMessage;
import com.hxf.mall.util.QiniuUtil;
import com.hxf.mall.util.UploadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //引入第一步的七牛配置
    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;

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
    public AMessage list_second_cate(@PathVariable int flbh1){
        AMessage aMessage = new AMessage();
        aMessage.setData(categoryService.getfl2List(flbh1));
        return aMessage;
    }

    @PostMapping("category/second")
    public AMessage add_second_cate(T_MALL_CLASS_2 class2){
        categoryService.add_second_cate(class2);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @PutMapping("category/second")
    public AMessage update_second_cate(@RequestBody String info){
        T_MALL_CLASS_2 class2 = JSON.parseObject(info, T_MALL_CLASS_2.class);
        categoryService.update_second_cate(class2);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @DeleteMapping("category/second/{id}")
    public AMessage delete_second_cate(@PathVariable int id){
        categoryService.delete_second_cate(id);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @GetMapping("category/tm/{flbh1}")
    public AMessage listtrademark(@PathVariable int flbh1){
        AMessage aMessage = new AMessage();
        aMessage.setData(categoryService.gettmList(flbh1));
        return aMessage;
    }

    @PostMapping("category/tm")
    public AMessage add_tm(int flbh1,T_MALL_TRADE_MARK tm, MultipartFile file){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.add_tm(tm);
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", tm.getId());
        map.put("flbh1",flbh1);
        categoryService.add_tm_class(map);
        String fileName = String.valueOf(tm.getId())+ "-" + flbh1;
        String imgUrl = qiniuUtil.uploadFile(file, fileName,"trademark/");
        tm.setUrl(imgUrl);
        categoryService.update_tm(tm);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

}
