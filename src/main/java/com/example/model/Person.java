package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;
	private int age;

	private final List<Book> books;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		books = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getBooks() {
		StringBuilder word = new StringBuilder("Books are ");
		for (int i = 0; i < books.size() - 1; i++) {
			word.append(books.get(i).getTitle()).append(", ");
		}
		word.append(books.get(books.size() - 1).getTitle());
		return word.toString();
	}

	public void setBooks(Book book) {
		books.add(book);
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
