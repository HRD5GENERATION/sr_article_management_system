package com.sr.service;

import java.util.List;

import com.sr.model.Article;

public interface ArticleService {
	List<Article> findAll();

	Article findOne(int id);

	boolean remove(int id);

	boolean save(Article article);
	
	boolean update(Article article);
}
