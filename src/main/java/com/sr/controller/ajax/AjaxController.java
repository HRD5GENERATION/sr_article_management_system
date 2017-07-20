package com.sr.controller.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

	@RequestMapping("")
	public String ajaxHome(){	
		return "ajax/index";
	}
	
	@RequestMapping("/add")
	public String ajaxAddArticle(){
		return "ajax/addarticle";
	}
	
}
