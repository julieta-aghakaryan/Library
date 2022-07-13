package com.example.Service;

import com.example.DAO.BookDAO;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

	private BookDAO bookDAO;

	public BookService() {
	}

	@Autowired
	public BookService(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public Book getBook(String title) {
		return bookDAO.getBook(title);
	}

	public void deleteBook(String title) {
		bookDAO.deleteBook(title);
	}

	public void updateBookName(String bookTitle, String title) {
		bookDAO.updateBookName(bookTitle, title);
	}

	public void addBook(String authorName, String title, int page, boolean published, int quantity) {
		bookDAO.addBook(authorName, title, page, published, quantity);
	}

	public List<String> getBooks() {
		return bookDAO.getBooks();
	}

	public List<Book> getBooksAsBooks() {
		return bookDAO.getBooksAsBooks();
	}

	public List<Book> getBookMap() {
		return bookDAO.getBookMap();
	}

	public boolean isPublished(String title) {
		return bookDAO.isPublished(title);
	}
}
