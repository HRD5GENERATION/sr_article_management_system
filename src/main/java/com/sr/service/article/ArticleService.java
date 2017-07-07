package com.sr.service.article;

import java.util.List;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.utility.Paging;

public interface ArticleService {

	List<Article> findAll();

	Article findOne(int id);

	boolean remove(int id);

	boolean save(Article article);

	boolean update(Article article);
	
	List<Article> findAllFilter(ArticleFilter filter, Paging paging);
}
