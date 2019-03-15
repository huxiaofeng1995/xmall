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
    public AMessage listTrademarkByClass1(@PathVariable int flbh1){
        AMessage aMessage = new AMessage();
        aMessage.setData(categoryService.gettmList(flbh1));
        return aMessage;
    }

    @GetMapping("category/tm")
    public AMessage listtrademark(){
        AMessage aMessage = new AMessage();
        aMessage.setData(categoryService.gettmList());
        return aMessage;
    }

    @PostMapping("category/tm")
    public AMessage add_tm(T_MALL_TRADE_MARK tm, MultipartFile file){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.add_tm(tm);
        //先将品牌名称插入表后拿到记录id，然后使用七牛插件上传品牌图片，拿到图片的外链url，再更新这条记录
        String fileName = String.valueOf(tm.getId());
        String imgUrl = qiniuUtil.uploadFile(file, fileName,TMUPLOADPATH);
        tm.setUrl(imgUrl);
        categoryService.update_tm(tm);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @DeleteMapping("category/tm/{id}")
    public AMessage delete_tm(@PathVariable int id){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.delete_tm(id);
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", id);
        //删除一级分类与其关联的中间表记录
        categoryService.delete_tm_class(id);
        String key = TMUPLOADPATH + String.valueOf(id);//七牛上文件的key值
        qiniuUtil.deleteFile(key);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @PostMapping("/category/tmclass")
    public AMessage add_tm_class(int pp_id, int flbh1){
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", pp_id);
        map.put("flbh1", flbh1);
        categoryService.add_tm_class(map);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }

    @DeleteMapping("category/tmclass/{id}/flbh1/{flbh1}")
    public AMessage delete_tm_class(@PathVariable int id, @PathVariable int flbh1){
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", id);
        map.put("flbh1", flbh1);
        categoryService.delete_tm_class2(map);
        AMessage aMessage = new AMessage();
        aMessage.setData("success");
        return aMessage;
    }
}
