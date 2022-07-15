package com.example.Service;

import com.example.model.Book;

import java.util.List;

public interface BookService {

	public Book getBook(String title);

	public void deleteBook(String title);

	public void updateBookName(String bookTitle, String title);

	public void addBook(String authorId, String title, int page, boolean published, int quantity, int price);
	public List<String> getBooks();

	public List<Book> getBooksAsBooks();

	public boolean isPublished(String title);
}

