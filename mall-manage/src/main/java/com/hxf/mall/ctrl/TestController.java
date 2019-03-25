package com.hxf.mall.ctrl;

import com.hxf.mall.test.Progress;
import com.hxf.mall.test.ProgressSingleton;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class TestController {


    @RequestMapping(value = "/up",method = RequestMethod.POST)
    public String up(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (!file.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("/") + "upload/";
            String fileName = file.getName();
            File target = new File(path + fileName);
            file.transferTo(target);
        }
        return "upload success";
    }


    @GetMapping(value = "/size")
    public Progress getProgress(HttpServletRequest request){
        Progress progress = (Progress) ProgressSingleton.get("status");
        System.out.println(progress + "controller");
        return progress;
    }
}
