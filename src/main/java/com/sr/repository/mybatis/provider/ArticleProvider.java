package com.sr.repository.mybatis.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.sr.model.filter.ArticleFilter;
import com.sr.utility.Paging;

public class ArticleProvider {
	
	public String findAll(){
		return new SQL(){{
			SELECT("A.id, A.title, A.description, A.thumbnail, C.name, C.id AS \"categoryId\"");
			FROM("tbarticle A");
			INNER_JOIN("tbcategory C ON C.id=A.category_id");
			ORDER_BY("A.id ASC");
		}}.toString();
	}
	
	public String findAllFilter(@Param("filter") ArticleFilter filter, @Param("paging") Paging paging){
		
		System.out.println("Filter" + filter);
		
		return new SQL(){{
			SELECT("A.id, A.title, A.description, A.thumbnail, C.name, C.id AS \"categoryId\"");
			FROM("tbarticle A");
			LEFT_OUTER_JOIN("tbcategory C ON C.id=A.category_id");
			
			if(filter.getCategoryId()!=null)
				WHERE("A.category_id = #{filter.categoryId}");
			
			if(filter.getTitle()!=null)
				WHERE("A.title ILIKE '%' || #{filter.title} || '%'");
			
			ORDER_BY("A.id ASC LIMIT #{paging.limit} OFFSET #{paging.offset}");
			
		}}.toString();
	}
	
	public String countAllFilter(@Param("filter") ArticleFilter filter){
		return new SQL(){{
			SELECT("COUNT(A.id)");
			FROM("tbarticle A");
			
			if(filter.getCategoryId()!=null)
				WHERE("A.category_id = #{filter.categoryId}");
			
			if(filter.getTitle()!=null)
				WHERE("A.title ILIKE '%' || #{filter.title} || '%'");
			
		}}.toString();
	}
}
