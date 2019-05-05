package com.hxf.mall.controller;

import com.hxf.mall.exception.OverSaleException;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;
import com.hxf.mall.service.AttrService;
import com.hxf.mall.service.CartService;
import com.hxf.mall.service.ListService;
import com.hxf.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class IndexController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value="/goto_login")
	public String goto_login(){
		return "login";
	}

	@GetMapping(value="/goto_register")
	public String goto_registry(){
		return "register";
	}

	@GetMapping(value="/registerSuccess")
	public String registerSuccess(){
		return "registerSuccess";
	}

	@GetMapping(value="/index")
	public String index(){
		return "home";
	}

	@GetMapping(value="/goto_search_class")
	public String goto_list(){
		return "list";
	}

	@GetMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index";
	}

	@GetMapping("goto_sku_detail")
	public String goto_detail(){
		return "detail";
	}

	@GetMapping(value="/cartSuccess")
	public String cartSuccess(){
		return "cartSuccess";
	}

	@GetMapping("goto_cart")
	public String goto_cart(){
		return "cart";
	}

	@GetMapping("/goto_checkOrder")
	public String goto_checkOrder(){
		return "orderCheck";
	}

	@GetMapping("/goto_pay")
	public String goto_aliPay(){
		return "alipay";
	}

	@GetMapping(value = "/pay/{oid}")
	public String pay_success(@PathVariable Integer oid, HttpSession session){
		//修改订单物流信息
		// 操作sku和库存
		OBJECT_T_MALL_ORDER order = (OBJECT_T_MALL_ORDER) session.getAttribute("order");
		try {
			orderService.pay(order);
		} catch (OverSaleException e) {
			e.printStackTrace();
			return "error";
		}
		return "paySuccess";
	}
}
