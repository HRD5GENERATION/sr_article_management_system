package com.sr.controller.thymeleaf;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.service.article.ArticleService;
import com.sr.service.category.CategoryService;
import com.sr.service.upload.FileUploadService;
import com.sr.utility.Paging;

@Controller
public class ArticleController {
	
	private ArticleService articleService;
	private FileUploadService uploadService;
	private CategoryService categoryService;
	
	@Autowired
	public ArticleController(ArticleService articleService, CategoryService categoryService, FileUploadService uploadService) {
		this.articleService = articleService;
		this.uploadService = uploadService;	
		this.categoryService = categoryService;
	}
	
	@GetMapping({"/", "/home", "/index", "/article", "/thymeleaf"})
	public String home(ArticleFilter filter, Paging paging, Model model){
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("articles", articleService.findAllFilter(filter, paging));
		model.addAttribute("filter", filter);
		model.addAttribute("paging", paging);
		
		return "article";
	}

	@GetMapping(value="/article/view/{id}")
	public String detailPage(ModelMap model, @PathVariable("id") Integer id){
		Article article = articleService.findOne(id);
		model.addAttribute("article", article);
		return "articledetail";
	}
	
	@GetMapping("/article/remove/{id}")
	public String remove(@PathVariable("id") Integer id, ArticleFilter filter, Paging paging, RedirectAttributes rda){
		if(articleService.remove(id)){
			System.out.println("success!");
			rda.addFlashAttribute("articleFilter", filter);
			rda.addFlashAttribute("paging", paging);
		}
		return "redirect:/article";
	}

	@GetMapping("/article/add")
	public String addPage(ModelMap model){
		model.addAttribute("article", new Article());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("addStatus", true);
		return "addarticle";
	}
	
	@PostMapping("/article/save")
	public String save(@RequestParam("file") MultipartFile file, 
					   @Valid Article article, BindingResult result, Model model){
		
		System.out.println(article);
		
		if(result.hasErrors()){
			model.addAttribute("article", article);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("addStatus", true);
			return "addarticle";
		}
		
		String thumbnail = uploadService.upload(file);
		article.setThumbnail(thumbnail);
		
		if(articleService.save(article)){
			System.out.println("success!");
		}
		return "redirect:/article";
	}
	
	@GetMapping("/article/edit/{id}")
	public String editPage(@PathVariable("id") Integer id, ModelMap model){
		model.addAttribute("article", articleService.findOne(id));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("addStatus", false);
		return "addarticle";
	}	
	
	@PostMapping("/article/update")
	public String update(@RequestParam("file") MultipartFile file,
				@Valid @ModelAttribute("article") Article article, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("article", article);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("addStatus", false);
			return "addarticle";
		}
		if(!file.isEmpty()){
			String thumbnail = uploadService.upload(file);
			article.setThumbnail(thumbnail);
		}
		if(articleService.update(article)){
			System.out.println("success!");
		}
		return "redirect:/article";
	}
}