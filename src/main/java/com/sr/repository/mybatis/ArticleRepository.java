package com.sr.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sr.model.Article;

@Repository
public interface ArticleRepository {

	@Select("SELECT id, title, description, thumbnail FROM tbarticle")
	/*@Results({
		@Result(property="id", column="id"),
		@Result(property="title", column="title"),
		@Result(property="description", column="description"),
		@Result(property="thumbnail", column="thumbnail")
	})*/
	List<Article> findAll();

	@Select("SELECT * FROM tbarticle WHERE id=#{id}")
	Article findOne(int id);

	@Delete("DELETE FROM tbarticle WHERE id=#{id}")
	boolean remove(int id);
	
	@Insert("INSERT INTO tbarticle(title, description, thumbnail) VALUES(#{title}, #{description}, #{thumbnail})")
	boolean save(Article article);
	
	@Update("UPDATE tbarticle SET title=#{title}, description=#{description}, thumbnail=#{thumbnail} WHERE id=#{id}")
	boolean update(Article article);
}
