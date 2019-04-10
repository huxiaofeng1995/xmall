package com.hxf.mall.controller;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.model.OBJECT_PRODUCT_SKU_INFO;
import com.hxf.mall.service.CartService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer cartCount = 0;
//        for(int i = 0;i < list_cart.size();i++){
//            T_MALL_SHOPPINGCAR c = list_cart.get(i);
//            cartCount += c.getTjshl();
//            //sum = sum.add(new BigDecimal(cart.getHj() + ""));
//        }
        session.setAttribute("cartCount",list_cart.size());
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

    @GetMapping("cartCount")
    public Result getCartCount(@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, HttpSession session){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

        if(user == null){
            //从cookie中获取
            if(!StringUtils.isBlank(list_cart_cookie)){
                try {
                    list_cart_cookie = URLDecoder.decode(list_cart_cookie , "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list_cart = JSON.parseArray(list_cart_cookie,T_MALL_SHOPPINGCAR.class);
            }
        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }
        session.setAttribute("cartCount",list_cart.size());
        return Result.success();
    }

    @GetMapping("carts")
    public Result getCartList(@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, HttpSession session){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        Map<String,Object> cartInfo = new HashMap<>();
        List<OBJECT_PRODUCT_SKU_INFO> sale_attr_list = new ArrayList<>();

        if(user == null){
            //从cookie中获取
            if(!StringUtils.isBlank(list_cart_cookie)){
                try {
                    list_cart_cookie = URLDecoder.decode(list_cart_cookie , "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list_cart = JSON.parseArray(list_cart_cookie,T_MALL_SHOPPINGCAR.class);
            }
        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            sale_attr_list.add(cartService.getSaleAttrBySkuId(cart.getSku_id()));
        }
        cartInfo.put("list_cart", list_cart);//购物车列表
        cartInfo.put("sale_attr_list", sale_attr_list);//购物车内产品销售属性列表
        cartInfo.put("sum", getMoney(list_cart));//购物车总金额
        session.setAttribute("cartCount",list_cart.size());
        cartInfo.put("cartSelectedCount",getSelectedCount(list_cart));
        return Result.success(cartInfo);
    }

    private BigDecimal getMoney(List<T_MALL_SHOPPINGCAR> list_cart) {
        BigDecimal sum = new BigDecimal("0");
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            if(cart.getShfxz().equals("1")) {//选中的才统计金额
                sum = sum.add(new BigDecimal(cart.getHj() + ""));//添加前要先转换
            }
        }
        return sum;
    }

    private int getSelectedCount(List<T_MALL_SHOPPINGCAR> list_cart){
        int count = 0;
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            if(cart.getShfxz().equals("1")) {
                count++;
            }
        }
        return count;
    }

    @PutMapping("cart")
    public Result changeCart(@RequestBody T_MALL_SHOPPINGCAR cart, @CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, HttpSession session, HttpServletResponse response){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        Map<String,Object> cartInfo = new HashMap<>();

        if(user == null){
            if(!StringUtils.isBlank(list_cart_cookie)){
                try {
                    list_cart_cookie = URLDecoder.decode(list_cart_cookie, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list_cart = JSON.parseArray(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

            }

        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }

        for(T_MALL_SHOPPINGCAR c : list_cart){
            //修改商品的选中状态，添加数量和合计价格
            if(c.getSku_id() == cart.getSku_id()){
                c.setShfxz(cart.getShfxz());
                c.setTjshl(cart.getTjshl());
                c.setHj(c.getTjshl() * c.getSku_jg());

                if(user == null) {
                    //更新cookie
                    String json = "";
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
                    cartService.update_cart(c);
                }
            }
        }
        cartInfo.put("list_cart", list_cart);//购物车列表
        cartInfo.put("sum", getMoney(list_cart));//购物车总金额
        session.setAttribute("cartCount",list_cart.size());
        cartInfo.put("cartSelectedCount",getSelectedCount(list_cart));
        return Result.success(cartInfo);
    }

    @DeleteMapping("cart/{sku_id}")
    public Result del_cart(@PathVariable Integer sku_id, @CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, HttpSession session, HttpServletResponse response){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        Map<String,Object> cartInfo = new HashMap<>();
        List<OBJECT_PRODUCT_SKU_INFO> sale_attr_list = new ArrayList<>();

        if(user == null){
            if(!StringUtils.isBlank(list_cart_cookie)){
                try {
                    list_cart_cookie = URLDecoder.decode(list_cart_cookie, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list_cart = JSON.parseArray(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

            }

        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }


        int size = list_cart.size();
//        for(T_MALL_SHOPPINGCAR c : list_cart){使用这种方法删除会报错ConcurrentModificationException
        for(int i = size -1;i >= 0;i--){
            //修改商品的选中状态，添加数量和合计价格
            T_MALL_SHOPPINGCAR c = list_cart.get(i);
            if(c.getSku_id() == sku_id){
                list_cart.remove(i);

                if(user == null) {
                    //更新cookie
                    String json = "";
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
                    cartService.delete_cart(c);
                }
            }
        }
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            sale_attr_list.add(cartService.getSaleAttrBySkuId(cart.getSku_id()));
        }
        cartInfo.put("list_cart", list_cart);//购物车列表
        cartInfo.put("sale_attr_list", sale_attr_list);//购物车内产品销售属性列表
        cartInfo.put("sum", getMoney(list_cart));//购物车总金额
        session.setAttribute("cartCount",list_cart.size());
        cartInfo.put("cartSelectedCount",getSelectedCount(list_cart));
        return Result.success(cartInfo);
    }
}
