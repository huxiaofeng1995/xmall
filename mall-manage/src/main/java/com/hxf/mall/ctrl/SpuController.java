package com.hxf.mall.ctrl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.service.SpuService;
import com.hxf.mall.util.QiniuUtil;
import com.hxf.mall.util.UploadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class SpuController {

    @Autowired
    private SpuService spuService;

    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;

    private final static String TMUPLOADPATH = "product/";

    @PostMapping("spu")
    public String spu_add(T_MALL_PRODUCT t_mall_product, MultipartFile[] files) {
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        //T_MALL_PRODUCT t_mall_product = JSON.parseObject(spu,T_MALL_PRODUCT.class);

        //上传图片
        List<String> imgs = new ArrayList<>();
        for(MultipartFile file:files){
            String originName = file.getOriginalFilename();
            //名字去重
            String filename = UUID.randomUUID().toString() + originName;
            String imgUrl = qiniuUtil.uploadFile(file, filename,TMUPLOADPATH);
            imgs.add(imgUrl);
        }

        //保存商品
        spuService.sava_spu(imgs, t_mall_product);
        return "success";
    }

    @GetMapping("spu-list")
    public PageInfo<T_MALL_PRODUCT> getSpuList(T_MALL_PRODUCT spu,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<T_MALL_PRODUCT> page = new PageInfo<>(spuService.getSpuList(spu));
        return page;
    }
}
