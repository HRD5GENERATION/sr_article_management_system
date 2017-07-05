package com.sr.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sr.model.Article;
import com.sr.model.Category;

@Repository
public interface CategoryRepository {
	
	@Select("SELECT id, name FROM tbcategory")
	@Results({
		@Result(property="id", column="id"),
		@Result(property="name", column="name")
	})
	List<Category> findAll();
	
	
	@Select("SELECT id, name FROM tbcategory")
	@Results({
		@Result(property="id", column="id"),
		@Result(property="name", column="name"),
		@Result(property="articles", column="id", many=@Many(select="findArticleByCategory"))
	})
	List<Category> findAllCategoryWithArticle();
	
	@Select("SELECT id, title, description, thumbnail FROM tbarticle WHERE category_id=#{id}")
	List<Article> findArticleByCategory(Integer id);
}
