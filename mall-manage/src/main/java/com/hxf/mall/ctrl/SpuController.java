package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_PRODUCT;
import com.hxf.mall.service.SpuService;
import com.hxf.mall.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class SpuController {

    @Autowired
    private SpuService spuService;

    @PostMapping("spu")
    @ResponseBody
    public String spu_add(String spu, MultipartFile[] files, HttpServletRequest request) {
        //String uploadPath = request.getServletContext().getRealPath("upload/img/product");
        T_MALL_PRODUCT t_mall_product = JSON.parseObject(spu,T_MALL_PRODUCT.class);
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());
        File upload = new File(path.getAbsolutePath(),"static/upload/img/product");
        String uploadPath = upload.getAbsolutePath();
        //上传图片
        List<String> imgs = FileUploadUtil.upload_img(uploadPath, files);
//        //保存商品
        spuService.sava_spu(imgs, t_mall_product);
        return "success";
    }
}
