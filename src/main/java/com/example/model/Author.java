package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Author {

	private final List<Book> books = new ArrayList<>();
	private String name;

	public Author(String name) {
		this.name = name;
	}

	public String getBooks() {
		StringBuilder word = new StringBuilder("Books are ");
		for (int i = 0; i < books.size() - 1; i++) {
			word.append(books.get(i).getTitle()).append(", ");
		}
		word.append(books.get(books.size() - 1).getTitle());
		return word.toString();
	}

	public void setBooks(Book books) {
		this.books.add(books);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
