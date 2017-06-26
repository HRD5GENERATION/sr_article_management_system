package com.sr.model;

public class Article {
	private int id;
	private String title;
	private String description;
	private String thumbnail;
	public Article() {
		super();
	}
	public Article(int id, String title, String description, String thumbnail) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", thumbnail=" + thumbnail
				+ "]";
	}
}
