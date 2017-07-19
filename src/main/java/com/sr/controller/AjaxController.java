package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sr.model.Article;
import com.sr.model.Category;
import com.sr.repository.mybatis.ArticleRepository;
import com.sr.repository.mybatis.CategoryRepository;
import com.sr.service.upload.FileUploadService;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping("/home")
	public String ajax(){	
		return "ajax/index";
	}
	
	@ResponseBody
	@GetMapping("/articles")
	public List<Article> ajaxHome(){
		return articleRepository.findAll();
	}
	
	/*@GetMapping("/ajax/test")
	public String test(Model model){
		model.addAttribute("articles", repo.findAll());
		return "article :: articleFragment";
	}*/
	
	
	@GetMapping("/article/add")
	public String addArticlePage(){
		return "ajax/addarticle";
	}
	
	@ResponseBody
	@PostMapping("/article")
	public boolean actionAddArticle(@RequestBody Article article){
		System.out.println(article);
		return articleRepository.save(article);
	}
	
	@ResponseBody
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	@ResponseBody
	@PostMapping("/article/upload")
	public String uploadFile(@RequestParam("thumbnail") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		return fileUploadService.upload(file);
	}
	
}
