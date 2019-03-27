package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.bean.T_MALL_PRODUCT_COLOR;
import com.hxf.mall.bean.T_MALL_PRODUCT_IMAGE;
import com.hxf.mall.bean.T_MALL_PRODUCT_VERSION;
import com.hxf.mall.service.SpuService;
import com.hxf.mall.to.ResponseData;
import com.hxf.mall.util.QiniuUtil;
import com.hxf.mall.util.ResponseDataUtil;
import com.hxf.mall.util.UploadFactory;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
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
    public ResponseData<String> add_spu(T_MALL_PRODUCT t_mall_product,String colors,String versions, MultipartFile[] files) {
        List<T_MALL_PRODUCT_COLOR> colorList = JSON.parseArray(colors,T_MALL_PRODUCT_COLOR.class);
        List<T_MALL_PRODUCT_VERSION> versionList = JSON.parseArray(versions,T_MALL_PRODUCT_VERSION.class);
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        //T_MALL_PRODUCT t_mall_product = JSON.parseObject(spu,T_MALL_PRODUCT.class);
        //上传图片
        List<String> imgs = new ArrayList<>();
        for(MultipartFile file:files){
            String originName = file.getOriginalFilename();
            //名字去重
            String filename = UUID.randomUUID().toString() + ".jpg";
            String imgUrl = qiniuUtil.uploadFile(file, filename,TMUPLOADPATH);
            imgs.add(imgUrl);
        }

        //保存商品
        spuService.sava_spu(imgs, t_mall_product);
        spuService.save_spu_color(t_mall_product.getId(),colorList);
        spuService.save_spu_version(t_mall_product.getId(),versionList);
        return ResponseDataUtil.buildSuccess("success");
    }

    @GetMapping("spu/{id}")
    public ResponseData get_spu(@PathVariable Integer id){
        return ResponseDataUtil.buildSuccess(spuService.get_spu(id));
    }



    @DeleteMapping("spu/{id}")
    public ResponseData del_spu(@PathVariable Integer id){
        List<T_MALL_PRODUCT_IMAGE> imgList = spuService.get_spu_img_list(id);
        for(T_MALL_PRODUCT_IMAGE img : imgList){
            QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                    this.bucketHostName, this.bucketName);
            String imgUrl = img.getUrl();
            String imgKey = imgUrl.replace(bucketHostName+"/","");
            qiniuUtil.deleteFile(imgKey);
            spuService.delet_spu_image(img.getId());
        }
        spuService.del_spu(id);
        return ResponseDataUtil.buildSuccess("success");
    }

    @PutMapping("spu")
    public ResponseData update_spu(@RequestBody String info){
        T_MALL_PRODUCT spu = JSON.parseObject(info, T_MALL_PRODUCT.class);
        spuService.update_spu(spu);
        return ResponseDataUtil.buildSuccess("success");
    }

    @GetMapping("spu-list")
    public ResponseData getSpuList(T_MALL_PRODUCT spu, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<T_MALL_PRODUCT> page = new PageInfo<>(spuService.getSpuList(spu));
        return ResponseDataUtil.buildSuccess(page);
    }

    @GetMapping("spu-imgs/{id}")
    public ResponseData getSpuImgList(@PathVariable Integer id){
        return ResponseDataUtil.buildSuccess(spuService.get_spu_img_list(id));
    }

    @PostMapping("spu-img")
    public ResponseData addSpuImg(T_MALL_PRODUCT_IMAGE img, MultipartFile file){
        QiniuUtil qiniuUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        String filename = UUID.randomUUID().toString() + ".jpg";
        String imgUrl = qiniuUtil.uploadFile(file, filename,TMUPLOADPATH);
        img.setUrl(imgUrl);
        spuService.add_spu_img(img);
        return ResponseDataUtil.buildSuccess("success");
    }

    @PutMapping("spu-img")
    public ResponseData updateSpuImg(@RequestBody String info){
        T_MALL_PRODUCT_IMAGE img = JSON.parseObject(info, T_MALL_PRODUCT_IMAGE.class);
        spuService.updateSpuImg(img);
        return ResponseDataUtil.buildSuccess("success");
    }

    @PutMapping("spu-img/main")
    public ResponseData setMainImg(@RequestBody String info){
        T_MALL_PRODUCT_IMAGE img = JSON.parseObject(info, T_MALL_PRODUCT_IMAGE.class);
        spuService.setMainImg(img);
        return ResponseDataUtil.buildSuccess("success");
    }

    @GetMapping("spu-colors/{id}")
    public ResponseData getColors(@PathVariable Integer id){
        return ResponseDataUtil.buildSuccess(spuService.get_spu_colors(id));
    }

    @GetMapping("spu-versions/{id}")
    public ResponseData getVersions(@PathVariable Integer id){
        return ResponseDataUtil.buildSuccess(spuService.get_spu_versions(id));
    }
}
