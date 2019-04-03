package com.hxf.mall.controller;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.service.CartService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("cart")
    public Result addCart(@RequestBody T_MALL_SHOPPINGCAR cart, HttpSession session, @CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, HttpServletResponse response){
        session.setAttribute("cart", cart);
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        String json = "";
        if(user == null){
            //用户未登陆
            if(StringUtils.isBlank(list_cart_cookie)){
                //cookie为空
                list_cart.add(cart);
            }else {
                //cookie不为空，更新
                try {
                    json = URLDecoder.decode(list_cart_cookie,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list_cart = JSON.parseArray(json, T_MALL_SHOPPINGCAR.class);
                if(contains(list_cart, cart)){
                    //cookie中存在，更新数量和总价格
                    for(T_MALL_SHOPPINGCAR ca : list_cart){
                        if(ca.getSku_id() == cart.getSku_id()){
                            ca.setTjshl(ca.getTjshl() + cart.getTjshl());
                            ca.setHj(ca.getTjshl() * ca.getSku_jg());
                        }
                    }
                }else {
                    //cookie中不存在，添加
                    list_cart.add(cart);
                }
            }
            try {
                json = URLEncoder.encode(JSON.toJSONString(list_cart),"utf-8");//解决cookie中文乱码的问题
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie("list_cart_cookie", json);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
        }else {
            //用户登录
            cart.setYh_id(user.getId());
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");//用户在登录的时候就同步了db和cookie的购物车并存到session中
            boolean flag = cartService.if_cart_exists(cart);

            if (!flag) {
                cartService.add_cart(cart);
                if (list_cart == null || list_cart.isEmpty()) {
                    list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
                    list_cart.add(cart);
                    session.setAttribute("list_cart_session", list_cart);//更新session
                } else {
                    list_cart.add(cart);
                }
            } else {
                for (int i = 0; i < list_cart.size(); i++) {
                    if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                        list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                        list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
                        // 老车，更新
                        cartService.update_cart(list_cart.get(i));
                    }
                }
            }
        }
        return Result.success();
    }

    private Boolean contains(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart){
        Boolean flag = false;
        for(int i = 0; i <list_cart.size();i++){
            if(list_cart.get(i).getSku_id() == cart.getSku_id()){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
