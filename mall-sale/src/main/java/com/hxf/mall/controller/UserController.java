package com.hxf.mall.controller;

import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.service.UserService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/login")
    public Result login(@RequestBody T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletResponse response){
        //查询数据库登录
        //省略了

        session.setAttribute("user",user);
        Cookie cookie = null;
        try {
            cookie = new Cookie("yh_nch", URLEncoder.encode(user.getYh_mch(),"utf-8"));//中文转码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setPath("/");//当类上加了requestMapping注解后，一定要setPath（"/"）
        cookie.setMaxAge(60*60*24);//必须设置过期时间，否则秒过期
        response.addCookie(cookie);
        return Result.success();
    }

    @PostMapping("register")
    public Result register(@RequestBody T_MALL_USER_ACCOUNT user){
        T_MALL_USER_ACCOUNT user_account = userService.getUserByUserName(user);
        if(user_account != null){
            return Result.fail("用户名称已被注册，请使用其他名称！");
        }else {
            userService.registry(user);
            return Result.success();
        }
    }
}
