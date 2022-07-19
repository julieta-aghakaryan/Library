package com.example.Service;

import com.example.model.Book;

import java.util.List;

public interface BookService {

	public Book getBook(String bookId);

	public void deleteBook(String bookId);

	public void updateBookName(String bookId, String title);

	public void addBook(Book book);

	public List<Book> getBooksAsBooks();

	public boolean isPublished(String title);

	public String getBooksNames();
}

