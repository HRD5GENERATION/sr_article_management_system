package com.sr.repository.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

import com.sr.model.filter.ArticleFilter;

public class ArticleProvider {
	
	public String findAll(){
		return new SQL(){{
			SELECT("A.id, A.title, A.description, A.thumbnail, C.name, C.id AS \"categoryId\"");
			FROM("tbarticle A");
			INNER_JOIN("tbcategory C ON C.id=A.category_id");
			ORDER_BY("A.id ASC");
		}}.toString();
	}
	
	public String findAllFilter(ArticleFilter filter){
		System.out.println(filter);
		return new SQL(){{
			SELECT("A.id, A.title, A.description, A.thumbnail, C.name, C.id AS \"categoryId\"");
			FROM("tbarticle A");
			INNER_JOIN("tbcategory C ON C.id=A.category_id");
			
			if(filter.getCategoryId()!=null)
				WHERE("A.category_id = #{categoryId}");
			
			if(filter.getTitle()!=null)
				WHERE("A.title ILIKE '%' || #{title} || '%'");
			
			ORDER_BY("A.id ASC");
		}}.toString();
	}
}
