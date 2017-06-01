package org.bread.worm.cms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	
	@RequestMapping("/admin")
	public String index(){
		
		System.out.println("admin/index");
		
		return "admin/index";
	}
}
