package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.model.Category;
import com.sr.repository.mybatis.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository cRepo;
	
	@GetMapping
	public List<Category> findAllCategory(){
		return cRepo.findAll();
	}
	
}
