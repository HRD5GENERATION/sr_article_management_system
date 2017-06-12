package com.sr.repository;

import java.util.List;

import com.sr.model.Article;

public interface ArticleRepository {
	List<Article> findAll();

	Article findOne(int id);

	boolean remove(int id);

	boolean save(Article article);
}
