package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_CLASS_1;
import com.hxf.mall.bean.T_MALL_CLASS_2;
import com.hxf.mall.bean.T_MALL_TRADE_MARK;
import com.hxf.mall.service.CategoryService;
import com.hxf.mall.to.ResponseData;
import com.hxf.mall.util.QiniuUtil;
import com.hxf.mall.util.ResponseDataUtil;
import com.hxf.mall.util.UploadFactory;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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


    @GetMapping("category/first")
    //@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResponseData list_first_cate(){
        return ResponseDataUtil.buildSuccess(categoryService.getfl1List());
    }

    @PostMapping("category/first")
    public ResponseData add_first_cate(T_MALL_CLASS_1 class1){
        categoryService.add_first_cate(class1);
        return ResponseDataUtil.buildSuccess("success");
    }

    @PutMapping("category/first")
    public ResponseData update_first_cate(@RequestBody String info){
        T_MALL_CLASS_1 class1 = JSON.parseObject(info, T_MALL_CLASS_1.class);
        categoryService.update_first_cate(class1);
        return ResponseDataUtil.buildSuccess("success");
    }

    @DeleteMapping("category/first/{id}")
    public ResponseData delete_first_cate(@PathVariable Integer id){
        categoryService.delete_first_cate(id);
        return ResponseDataUtil.buildSuccess("success");
    }

    @GetMapping("category/second/{flbh1}")
    public ResponseData list_second_cate(@PathVariable Integer flbh1){
        return ResponseDataUtil.buildSuccess(categoryService.getfl2List(flbh1));
    }

    @PostMapping("category/second")
    public ResponseData add_second_cate(T_MALL_CLASS_2 class2){
        categoryService.add_second_cate(class2);
        return ResponseDataUtil.buildSuccess("success");    }

    @PutMapping("category/second")
    public ResponseData update_second_cate(@RequestBody String info){
        T_MALL_CLASS_2 class2 = JSON.parseObject(info, T_MALL_CLASS_2.class);
        categoryService.update_second_cate(class2);
        return ResponseDataUtil.buildSuccess("success");
    }

    @DeleteMapping("category/second/{id}")
    public ResponseData delete_second_cate(@PathVariable Integer id){
        categoryService.delete_second_cate(id);
        return ResponseDataUtil.buildSuccess("success");
    }

    @GetMapping("category/tm/{flbh1}")
    public ResponseData listTrademarkByClass1(@PathVariable Integer flbh1){
        return ResponseDataUtil.buildSuccess(categoryService.gettmList(flbh1));
    }

    @GetMapping("category/tm")
    public ResponseData listtrademark(){
        return ResponseDataUtil.buildSuccess(categoryService.gettmList());
    }

    @PostMapping("category/tm")
    public ResponseData add_tm(T_MALL_TRADE_MARK tm, MultipartFile file){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.add_tm(tm);
        //先将品牌名称插入表后拿到记录id，然后使用七牛插件上传品牌图片，拿到图片的外链url，再更新这条记录
        String fileName = String.valueOf(tm.getId());
        String imgUrl = qiniuUtil.uploadFile(file, fileName,TMUPLOADPATH);
        tm.setUrl(imgUrl);
        categoryService.update_tm(tm);
        return ResponseDataUtil.buildSuccess("success");
    }

    @DeleteMapping("category/tm/{id}")
    public ResponseData delete_tm(@PathVariable Integer id){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        categoryService.delete_tm(id);
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", id);
        //删除一级分类与其关联的中间表记录
        categoryService.delete_tm_class(id);
        String key = TMUPLOADPATH + String.valueOf(id);//七牛上文件的key值
        qiniuUtil.deleteFile(key);
        return ResponseDataUtil.buildSuccess("success");
    }

    @PostMapping("/category/tmclass")
    public ResponseData add_tm_class(Integer pp_id, Integer flbh1){
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", pp_id);
        map.put("flbh1", flbh1);
        categoryService.add_tm_class(map);
        return ResponseDataUtil.buildSuccess("success");
    }

    @DeleteMapping("category/tmclass/{id}/flbh1/{flbh1}")
    public ResponseData delete_tm_class(@PathVariable Integer id, @PathVariable Integer flbh1){
        Map<String,Object> map = new HashMap<>();
        map.put("pp_id", id);
        map.put("flbh1", flbh1);
        categoryService.delete_tm_class2(map);
        return ResponseDataUtil.buildSuccess("success");
    }
}
