package com.hxf.mall.controller;

import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CartController {

    @PostMapping("cart")
    public Result addCart(@RequestBody T_MALL_SHOPPINGCAR cart, HttpSession session){
        session.setAttribute("cart", cart);
        return Result.success();
    }
}
