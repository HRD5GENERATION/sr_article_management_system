package com.sr.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.model.Category;
import com.sr.service.category.CategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryRestController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return categoryService.findAll();
	}
	
}
