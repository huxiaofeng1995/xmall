package com.hxf.mall.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
