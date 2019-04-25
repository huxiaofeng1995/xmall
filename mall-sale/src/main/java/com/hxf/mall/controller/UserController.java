package com.hxf.mall.controller;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.service.CartService;
import com.hxf.mall.service.UserService;
import com.hxf.mall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping(value="/login")
    public Result login(@RequestBody T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletResponse response, @CookieValue(value = "list_cart_cookie",required = false,defaultValue = "") String list_cart_cookie){
        T_MALL_USER_ACCOUNT user_account = userService.getUserByUserName(user);
        if(user_account != null && user.getYh_mm().equals(user_account.getYh_mm())) {
            session.setAttribute("user", user_account);
            Cookie cookie = null;
            try {
                cookie = new Cookie("yh_nch", URLEncoder.encode(user.getYh_mch(), "utf-8"));//中文转码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setPath("/");//当类上加了requestMapping注解后，一定要setPath（"/"）
            cookie.setMaxAge(60 * 60 * 24);//必须设置过期时间，否则秒过期
            response.addCookie(cookie);

            combine_cart(user_account, response, session, list_cart_cookie);
            return Result.success();
        }else {
            return Result.fail("用户名密码有误");
        }
    }

    private void combine_cart(T_MALL_USER_ACCOUNT user, HttpServletResponse response, HttpSession session, String list_cart_cookie) {
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        List<T_MALL_SHOPPINGCAR> list_cart_db = new ArrayList<T_MALL_SHOPPINGCAR>();
        try {
            list_cart_cookie = URLDecoder.decode(list_cart_cookie , "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        list_cart = JSON.parseArray(list_cart_cookie,T_MALL_SHOPPINGCAR.class);//在cookie中的购物车
        list_cart_db = cartService.get_cart_list_by_user(user);//在db中的购物车
        if(StringUtils.isBlank(list_cart_cookie)){
            //cookie中没有数据，则直接取db中的购物车存到session中即可
        }else {
            for(T_MALL_SHOPPINGCAR cart : list_cart){
                cart.setYh_id(user.getId());//注意cookie中取出来的cart是没有用户id的，这里我们要设置用户id再执行插入
                boolean flag = cartService.if_cart_exists(cart);
                if(!flag) {
                    //db不存在
                    cartService.add_cart(cart);
                }else {
                    //db存在
                    for(T_MALL_SHOPPINGCAR cart_db : list_cart_db){
                        if(cart_db.getSku_id() == cart.getSku_id()){
                            cart_db.setTjshl(cart_db.getTjshl()+cart.getTjshl());
                            cart_db.setHj(cart_db.getTjshl()*cart_db.getSku_jg());
                            cartService.update_cart(cart_db);
                        }
                    }
                }
            }

        }
        //同步session,清空cookie
        list_cart = cartService.get_cart_list_by_user(user);
        session.setAttribute("list_cart_session", list_cart);
        session.setAttribute("cartCount",list_cart.size());
        response.addCookie(new Cookie("list_cart_cookie",""));
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

    @GetMapping("check-user-login")
    public Result checkUserLogin(HttpSession session){
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        if(user == null){
            return Result.fail("用户未登录");
        }else {
            return Result.success(user);
        }
    }

    @PostMapping("address")
    public Result addAddreess(@RequestBody T_MALL_ADDRESS address,HttpSession session){
        T_MALL_USER_ACCOUNT user_account = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        address.setYh_id(user_account.getId());
        userService.add_address(address);
        return Result.success("success");
    }
}
