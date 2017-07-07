package com.sr.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.repository.mybatis.ArticleRepository;
import com.sr.utility.Paging;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article findOne(int id) {
		return articleRepository.findOne(id);
	}

	@Override
	public boolean remove(int id) {
		return articleRepository.remove(id);
	}

	@Override
	public boolean save(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public boolean update(Article article) {
		return articleRepository.update(article);
	}

	@Override
	public List<Article> findAllFilter(ArticleFilter filter, Paging paging) {
		paging.setTotalCount(articleRepository.countAllFilter(filter));
		return articleRepository.findAllFilter(filter, paging);
	}
	
	

}
