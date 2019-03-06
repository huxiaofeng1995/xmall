package com.hxf.mall.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {

    public static List<String> upload_img(String path, MultipartFile[] files){
        List<String> list = new ArrayList<String>();
        try {
            for (MultipartFile file : files) {
                if(!file.isEmpty()) {//非空
                    String originName = file.getOriginalFilename();
                    //名字去重
                    String filename = UUID.randomUUID().toString() + originName;
                    String uploadPath = path + File.separator + filename;
                    File upfile = new File(uploadPath);
                    if(!upfile.getParentFile().exists()){
                        upfile.getParentFile().mkdirs();
                    }
//                    image.transferTo(file);
//                    BufferedImage img = ImageUtil.change2jpg(file);
//                    ImageIO.write(img, "jpg", file);
                    file.transferTo(upfile);
                    BufferedImage img = ImageUtil.change2jpg(upfile);
                    ImageIO.write(img, "jpg", upfile);
                    list.add(filename);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;//返回的应该是文件名的集合，不包含路径名
    }
}
