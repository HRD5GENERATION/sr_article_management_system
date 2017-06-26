package com.sr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.model.Article;
import com.sr.repository.mybatis.ArticleRepository;

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

}
