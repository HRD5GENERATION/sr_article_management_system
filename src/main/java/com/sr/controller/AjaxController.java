package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sr.model.Article;
import com.sr.repository.mybatis.ArticleRepository;

@Controller
public class AjaxController {

	@Autowired
	private ArticleRepository repo;
	
	@GetMapping("/ajax")
	public String ajax(){	
		return "ajax/index";
	}
	
	@ResponseBody
	@GetMapping("/ajax/articles")
	public List<Article> ajaxHome(){
		return repo.findAll();
	}
	
	@GetMapping("/ajax/test")
	public String test(Model model){
		model.addAttribute("articles", repo.findAll());
		return "article :: articleFragment";
	}
	
}
