
package com.sr.controller.thymeleafajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sr.model.filter.ArticleFilter;
import com.sr.service.article.ArticleService;
import com.sr.service.category.CategoryService;
import com.sr.utility.Paging;

@Controller
@RequestMapping("/thymeleaf-ajax")
public class ThymeleafAjaxController {

	private ArticleService articleService;
	private CategoryService categoryService;
	
	@Autowired
	public ThymeleafAjaxController(ArticleService articleService, CategoryService categoryService) {
		this.articleService = articleService;
		this.categoryService = categoryService;
	}
	
	@RequestMapping("")
	public String home(ArticleFilter filter, Paging paging, Model model){
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("articles", articleService.findAllFilter(filter, paging));
		model.addAttribute("paging", paging);
		
		return "thymeleaf-ajax/index";
	}
	
	@GetMapping("/articles")
	public String pageClick(ArticleFilter filter, Paging paging, Model model){
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("articles", articleService.findAllFilter(filter, paging));
		model.addAttribute("paging", paging);	
		
		return "thymeleaf-ajax/index :: articleTable";
	}
	
	@ResponseBody
	@DeleteMapping("/articles/{id}")
	public boolean delete(@PathVariable("id") Integer id){
		return articleService.remove(id);
	}
}
