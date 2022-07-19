package com.example.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
	@Id
	private ObjectId id;
	private String name;
	private int age;
	@JsonSerialize(using = ToStringSerializer.class)
	private List<String> books;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		books = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
