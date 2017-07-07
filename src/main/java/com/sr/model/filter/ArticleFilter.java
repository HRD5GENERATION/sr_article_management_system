package com.sr.model.filter;

public class ArticleFilter {
	
	private String title;
	private Integer categoryId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "ArticleFilter [title=" + title + ", categoryId=" + categoryId + "]";
	}
}
