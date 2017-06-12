package com.sr.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import com.github.javafaker.Faker;
import com.sr.model.Article;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository{

	private List<Article> articles;
	
	public ArticleRepositoryImpl() {
		Faker faker = new Faker(Locale.ENGLISH);
		articles = new ArrayList<Article>();
		for(int i=0; i<20; i++){
			Article article = new Article();
			article.setId(i+1);
			article.setTitle(faker.book().title());
			article.setDescription(faker.lorem().sentence());
			article.setThumbnail("https://camo.mybb.com/e01de90be6012adc1b1701dba899491a9348ae79/687474703a2f2f7777772e6a71756572797363726970742e6e65742f696d616765732f53696d706c6573742d526573706f6e736976652d6a51756572792d496d6167652d4c69676874626f782d506c7567696e2d73696d706c652d6c69676874626f782e6a7067");
			articles.add(article);
		}		
	}
	
	@Override
	public List<Article> findAll() {
		return articles;
	}

	@Override
	public Article findOne(int id) {
		return articles.get(id);
	}

	@Override
	public boolean remove(int id) {
		return false;
	}

	@Override
	public boolean save(Article article) {
		// TODO Auto-generated method stub
		return false;
	}

}
