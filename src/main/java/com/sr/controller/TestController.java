package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.service.article.ArticleService;
import com.sr.utility.Paging;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private ArticleService service;
	
	@Autowired
	public TestController(ArticleService service) {
		this.service = service;
	}
	
	@GetMapping("/article")
	public String home(ArticleFilter filter, Paging paging, Model model){
		
		List<Article> articles = service.findAllFilter(filter, paging);
		
		model.addAttribute("articles", articles);
		model.addAttribute("paging", paging);
		
		return "article";
	}
	
	
}
