package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sr.model.Article;
import com.sr.service.ArticleService;
import com.sr.service.upload.UploadService;

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
	

	@RequestMapping(value = "/article/view", method=RequestMethod.GET)
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
	
	@GetMapping("/article/remove/{id}")
	public String remove(ModelMap model, @PathVariable("id") Integer id){
		if(articleService.remove(id)){
			System.out.println("success!");
		}
		return "redirect:/article";
	}
	
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/article/save")
	public String save(
			@RequestParam("file") MultipartFile file,  
			@ModelAttribute("article") Article article, 
			BindingResult result){
		
		if(result.hasErrors()){
			return "redirect:/article/add";
		}
		
		String thumbnail = uploadService.upload(file);
		article.setThumbnail(thumbnail);
		
		if(articleService.save(article)){
			System.out.println("success!");
		}
		return "redirect:/article";
	}
	
	
	
	
	
	
	
	@GetMapping("/article/add")
	public String addPage(ModelMap model){
		model.addAttribute("article", new Article());
		
		//model.addAttribute("action", "/article/save");
		model.addAttribute("addStatus", true);
		return "addarticle";
	}	
	
	@GetMapping("/article/edit/{id}")
	public String editPage(@PathVariable("id") Integer id, ModelMap model){
		model.addAttribute("article", articleService.findOne(id));

		//model.addAttribute("action", "/article/update");
		model.addAttribute("addStatus", false);
		
		return "addarticle";
		//return "updatearticle";
	}	
	
	
	@PostMapping("/article/update")
	public String update(@ModelAttribute("article") Article article, BindingResult result){
		if(result.hasErrors()){
			return "redirect:/article/add";
		}
		if(articleService.update(article)){
			System.out.println("success!");
		}
		return "redirect:/article";
	}
}
