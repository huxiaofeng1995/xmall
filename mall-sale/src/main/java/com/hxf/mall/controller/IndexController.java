package com.hxf.mall.controller;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_ORDER_INFO;
import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.model.OBJECT_T_MALL_FLOW;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;
import com.hxf.mall.service.AttrService;
import com.hxf.mall.service.CartService;
import com.hxf.mall.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class IndexController {
	@Autowired
	private AttrService attrService;

	@Autowired
	private ListService listService;

	@Autowired
	private CartService cartService;

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
}
