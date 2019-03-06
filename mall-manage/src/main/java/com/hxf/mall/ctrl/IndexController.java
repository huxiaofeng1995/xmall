package com.hxf.mall.ctrl;

import com.hxf.mall.bean.T_MALL_PRODUCT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {
	
	@GetMapping(value="/index")
	public String index(){
		return "admin/main";
	}

	@GetMapping("goto_spu")
	public String goto_spu(){
		return "admin/spu";
	}

	@GetMapping("goto_spu_add")
	public String goto_spuAdd(T_MALL_PRODUCT spu,Map model){
		model.put("spu", spu);
		return "admin/spuAdd";
	}
}
