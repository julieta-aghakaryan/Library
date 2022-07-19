package com.example.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.List;

@Entity
public class Shopping {

	@Id
	private ObjectId id;
	public String personId;
	@JsonSerialize(using = ToStringSerializer.class)
	public List<String> books;

	public int totalPrice;

	public Shopping() {
	}

	public Shopping(String personId, List<String> books, int totalPrice) {
		this.personId = personId;
		this.books = books;
		this.totalPrice = totalPrice;
	}

	public String getPerson() {
		return personId;
	}

	public void setPerson(String personId) {
		this.personId = personId;
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
