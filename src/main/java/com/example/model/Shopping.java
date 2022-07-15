package com.example.model;

import com.example.DAO.ShoppingDAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.List;

@Entity
public class Shopping {

	@Id
	private ObjectId id;
	@Reference
	@JsonProperty("personName")
	public Person person;
	@Reference
	public List<Book> books;

	public int totalPrice;

	public Shopping(){}
	public Shopping(Person person, List<Book> books, int totalPrice) {
		this.person = person;
		this.books = books;
		this.totalPrice = totalPrice;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
