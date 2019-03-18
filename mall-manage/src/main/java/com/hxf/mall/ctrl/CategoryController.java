package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.service.CategoryService;
import com.hxf.mall.to.AMessage;
import com.hxf.mall.util.QiniuUtil;
import com.hxf.mall.util.UploadFactory;
import org.apache.ibatis.annotations.Delete;
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

    private final static String TMUPLOADPATH = "trademark/";

    @GetMapping("listcategory1")
    public List<T_MALL_CLASS_1> list_cate1(){
        return categoryService.getfl1List();
    }

    @GetMapping("listcategory2/{flbh1}")
    public List<T_MALL_CLASS_2> list_cate2(@PathVariable Integer flbh1){
        return categoryService.getfl2List(flbh1);
    }

    @GetMapping("listtrademark/{flbh1}")
    public List<T_MALL_TRADE_MARK> list_trademark(@PathVariable Integer flbh1){
        return categoryService.gettmList(flbh1);
    }

    @GetMapping("category/first")
    public List<T_MALL_CLASS_1> list_first_cate(){
        return categoryService.getfl1List();
    }

    @PostMapping("category/first")
    public String add_first_cate(T_MALL_CLASS_1 class1){
        categoryService.add_first_cate(class1);
        return "success";
    }

    @PutMapping("category/first")
    public String update_first_cate(@RequestBody String info){
        T_MALL_CLASS_1 class1 = JSON.parseObject(info, T_MALL_CLASS_1.class);
        categoryService.update_first_cate(class1);
        return "success";
    }

    @DeleteMapping("category/first/{id}")
    public String delete_first_cate(@PathVariable Integer id){
        categoryService.delete_first_cate(id);
        return "success";
    }

    @GetMapping("category/second/{flbh1}")
    public List<T_MALL_CLASS_2> list_second_cate(@PathVariable Integer flbh1){
        return categoryService.getfl2List(flbh1);
    }

    @PostMapping("category/second")
    public String add_second_cate(T_MALL_CLASS_2 class2){
        categoryService.add_second_cate(class2);
        return "success";
    }

    @PutMapping("category/second")
    public String update_second_cate(@RequestBody String info){
        T_MALL_CLASS_2 class2 = JSON.parseObject(info, T_MALL_CLASS_2.class);
        categoryService.update_second_cate(class2);
        return "success";
    }

    @DeleteMapping("category/second/{id}")
    public String delete_second_cate(@PathVariable Integer id){
        categoryService.delete_second_cate(id);
        return "success";
    }

    @GetMapping("category/tm/{flbh1}")
    public List<T_MALL_TRADE_MARK> listTrademarkByClass1(@PathVariable Integer flbh1){
        return categoryService.gettmList(flbh1);
    }

    @GetMapping("category/tm")
    public List<T_MALL_TRADE_MARK> listtrademark(){
        return categoryService.gettmList();
    }

    @PostMapping("category/tm")
    public String add_tm(T_MALL_TRADE_MARK tm, MultipartFile file){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.add_tm(tm);
        //先将品牌名称插入表后拿到记录id，然后使用七牛插件上传品牌图片，拿到图片的外链url，再更新这条记录
        String fileName = String.valueOf(tm.getId());
        String imgUrl = qiniuUtil.uploadFile(file, fileName,TMUPLOADPATH);
        tm.setUrl(imgUrl);
        categoryService.update_tm(tm);
        return "success";
    }

    @DeleteMapping("category/tm/{id}")
    public String delete_tm(@PathVariable Integer id){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.delete_tm(id);
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", id);
        //删除一级分类与其关联的中间表记录
        categoryService.delete_tm_class(id);
        String key = TMUPLOADPATH + String.valueOf(id);//七牛上文件的key值
        qiniuUtil.deleteFile(key);
        return "success";
    }

    @PostMapping("/category/tmclass")
    public String add_tm_class(Integer pp_id, Integer flbh1){
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", pp_id);
        map.put("flbh1", flbh1);
        categoryService.add_tm_class(map);
        return "success";
    }

    @DeleteMapping("category/tmclass/{id}/flbh1/{flbh1}")
    public String delete_tm_class(@PathVariable Integer id, @PathVariable Integer flbh1){
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", id);
        map.put("flbh1", flbh1);
        categoryService.delete_tm_class2(map);
        return "success";
    }
}
