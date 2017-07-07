package com.sr.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sr.model.Article;
import com.sr.model.filter.ArticleFilter;
import com.sr.repository.mybatis.provider.ArticleProvider;
import com.sr.utility.Paging;

@Repository
public interface ArticleRepository {

	@SelectProvider(method="findAll", type=ArticleProvider.class)
	@Results({
		@Result(property="category.id", column="categoryId"),
		@Result(property="category.name", column="name")
	})
	List<Article> findAll();

	@Select("SELECT * FROM tbarticle WHERE id=#{id}")
	Article findOne(int id);

	@Delete("DELETE FROM tbarticle WHERE id=#{id}")
	boolean remove(int id);
	
	@Insert("INSERT INTO tbarticle(title, description, thumbnail) VALUES(#{title}, #{description}, #{thumbnail})")
	boolean save(Article article);
	
	@Update("UPDATE tbarticle SET title=#{title}, description=#{description}, thumbnail=#{thumbnail} WHERE id=#{id}")
	boolean update(Article article);
	
	
	
	@SelectProvider(method = "findAllFilter", type = ArticleProvider.class)
	@Results({
		@Result(property="category.id", column="categoryId"),
		@Result(property="category.name", column="name")
	})
	List<Article> findAllFilter(@Param("filter") ArticleFilter filter, @Param("paging") Paging paging);
	
	@SelectProvider(method = "countAllFilter", type = ArticleProvider.class)
	Integer countAllFilter(@Param("filter") ArticleFilter filter);
	
	
}
