package com.hxf.mall.controller;

import com.hxf.mall.service.AttrService;
import com.hxf.mall.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {
	@Autowired
	private AttrService attrService;

	@Autowired
	private ListService listService;

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
}
