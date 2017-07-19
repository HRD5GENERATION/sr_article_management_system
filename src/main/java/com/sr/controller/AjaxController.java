package com.sr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxController {

	@GetMapping("ajax/home")
	public String ajax(){	
		return "ajax/index";
	}
	
	@GetMapping("ajax/article/add")
	public String addArticlePage(){
		return "ajax/addarticle";
	}
	
}
