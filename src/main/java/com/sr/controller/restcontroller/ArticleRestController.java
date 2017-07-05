package com.sr.controller.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.repository.mybatis.ArticleRepository;

@RestController
public class ArticleRestController {
	
	@Autowired
	private ArticleRepository aRepo;
	
	//http://localhost:8080/article/rest?title=a&categoryId=1
	@GetMapping("/article/rest")
	public List<Article> findAllArticleFilter(ArticleFilter filter){
		return aRepo.findAllFilter(filter);
	}
}
