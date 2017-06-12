package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sr.model.Article;
import com.sr.service.ArticleService;

@Controller
public class ArticleController {
	
	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping(value = "/article", method=RequestMethod.GET)
	public String homePage(ModelMap model){
		List<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);
		return "article";
	}
	

	@RequestMapping(value = "/article/aid", method=RequestMethod.GET)
	public String detailPage(ModelMap model, @RequestParam("id") Integer id){
		System.out.println(id);
		Article article = articleService.findOne(id);
		model.addAttribute("article", article);
		return "articledetail";
	}
	
	@GetMapping("/article/{id}")
	public String detailPage1(ModelMap model, @PathVariable("id") Integer id){
		System.out.println(id);
		Article article = articleService.findOne(id);
		model.addAttribute("article", article);
		return "articledetail";
	}
}
