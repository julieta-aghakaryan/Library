package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity
public class Book {
	@Id
	private ObjectId id;
	private String authorId;
	private int quantity;
	@JsonProperty("bookTitle")
	private String title;
	private int pages;
	private int price;
	private boolean published;

	public Book(){}
	public Book(String  authorId, String title, int pages, boolean published, int quantity, int price) {
		this.authorId = authorId;
		this.title = title;
		this.pages = pages;
		this.published = published;
		this.quantity = quantity;
		this.price = price;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
