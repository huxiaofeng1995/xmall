package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.to.AMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("admin/login")
    private String login(@RequestBody String username){

        //省略数据库查询操作，待后期完善


        AMessage aMessage = new AMessage();
        Map<String,Object> data = new HashMap<>();
        data.put("tokenHead","test");
        data.put("token",System.currentTimeMillis());
        aMessage.setData(data);
        return JSON.toJSONString(aMessage);
    }

    @GetMapping("admin/info")
    private String getUserInfo(HttpServletRequest request){
        //获取请求token，验证该token用户是否登录
        String token = request.getHeader("Authorization");

        //省略数据库查询操作，待后期完善


        AMessage aMessage = new AMessage();
        aMessage.setCode(200);
        Map<String,Object> data = new HashMap<>();
        data.put("username","胡晓峰");
        List<String>  roles = new ArrayList<>();
        roles.add("admin");
        data.put("roles",roles);
        data.put("icon","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        aMessage.setData(data);
        return JSON.toJSONString(aMessage);
    }

}
