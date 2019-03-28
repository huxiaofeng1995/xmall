package com.hxf.mall.controller;

import com.hxf.mall.bean.OBJECT_T_MALL_ATTR;
import com.hxf.mall.bean.OBJECT_T_MALL_SKU;
import com.hxf.mall.service.AttrService;
import com.hxf.mall.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
	@Autowired
	private AttrService attrService;

	@Autowired
	private ListService listService;

	@RequestMapping(value="/goto_login")
	public String goto_login(){
		return "login";
	}

	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}

	@RequestMapping(value="/goto_search_class")
	public String goto_list(int flbh2,Map map){
		map.put("flbh2",flbh2);
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
		map.put("list_attr",list_attr);

		List<OBJECT_T_MALL_SKU> list_sku =listService.get_sku_list(flbh2);
		map.put("list_sku",list_sku);
		map.put("count",list_sku.size());
		return "list";
	}
}
