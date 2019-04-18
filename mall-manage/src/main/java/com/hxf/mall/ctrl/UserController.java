package com.hxf.mall.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.service.UserService;
import com.hxf.mall.to.ResponseData;
import com.hxf.mall.to.ResultEnums;
import com.hxf.mall.util.JWTUtil;
import com.hxf.mall.util.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("admin/login")
    private ResponseData login(@RequestBody String userInfo){

        //省略数据库查询操作，待后期完善
        JSONObject object = JSON.parseObject(userInfo);
        String username = object.getString("username");
        String password = object.getString("password");
        String pwd = userService.getPassword(username);
        if(pwd != null && pwd.equals(password)) {
            Map<String, Object> data = new HashMap<>();
            data.put("token", jwtUtil.createToken(username));
            return ResponseDataUtil.buildSuccess(data);
        }else{
            return ResponseDataUtil.buildError(ResultEnums.LOGIN_ERROR);
        }
    }

    @GetMapping("admin/info")
    private ResponseData getUserInfo(HttpServletRequest request){
        //获取请求token，验证该token用户是否登录
        String token = request.getHeader("Authorization");

        //省略数据库查询操作，待后期完善


        Map<String,Object> data = new HashMap<>();
        data.put("username","胡晓峰");
        List<String>  roles = new ArrayList<>();
        roles.add("admin");
        data.put("roles",roles);
        data.put("icon","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return ResponseDataUtil.buildSuccess(data);
    }

    @GetMapping("/unauthorized")
    private ResponseData unauthorized(){
        return ResponseDataUtil.buildError(ResultEnums.PERMISSION_DENY);
    }

}
