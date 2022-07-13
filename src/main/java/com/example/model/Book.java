package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book {
	@JsonIgnore
	private Author author;
	private int quantity;
	private String title;
	private int pages;
	private boolean published;

	public Book(Author author, String title, int pages, boolean published, int quantity) {
		this.author = author;
		this.title = title;
		this.pages = pages;
		this.published = published;
		this.quantity = quantity;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
}
