package com.hxf.mall.controller;

import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class LoginController {

    @PostMapping(value="/login")
    public String login(T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletRequest request, HttpServletResponse response,ModelMap map){
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
        return "redirect:/index";
    }
}
