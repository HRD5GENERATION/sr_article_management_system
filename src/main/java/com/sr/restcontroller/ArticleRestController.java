package com.sr.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.service.article.ArticleService;
import com.sr.utility.Paging;

@RestController
@RequestMapping("/api/v1")
public class ArticleRestController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/articles")
	public Map<String, Object> findAll(ArticleFilter filter, Paging paging){
		List<Article> articles = articleService.findAllFilter(filter, paging);
		Map<String, Object> response = new HashMap<>();
		if(!articles.isEmpty()){
			response.put("message", "Success!");
			response.put("status", true);
			response.put("data", articles);
			response.put("paging", paging);
		}else{
			response.put("message", "No article!");
			response.put("status", false);
		}
		return response;
	}
	
	@PostMapping("/articles")
	public Map<String, Object> save(@RequestBody Article article){
		Map<String, Object> response = new HashMap<>();
		if(articleService.save(article)){
			response.put("message", "Added Successfully!");
			response.put("status", true);
		}else{
			response.put("message", "Add Failed!");
			response.put("status", false);
		}
		return response;
	}
	
	@GetMapping("/articles/{id}")
	public Map<String, Object> findOne(@PathVariable("id") Integer id){
		Map<String, Object> response = new HashMap<>();
		if(articleService.findOne(id) != null){
			response.put("message", "Article Found!");
			response.put("status", true);
		}else{
			response.put("message", "Article Not Found!");
			response.put("status", false);
		}
		return response;
	}
	
	@PutMapping("/articles")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Article article){
		Map<String, Object> response = new HashMap<>();
		if(articleService.update(article)){
			response.put("message", "Updated Successfully!");
			response.put("status", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		response.put("message", "Update Failed!");
		response.put("status", false);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Integer id){

		Map<String, Object> response = new HashMap<>();
		if(articleService.remove(id)){
			response.put("message", "Removed Successfully!");
			response.put("status", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		response.put("message", "Removed Failed!");
		response.put("status", false);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
