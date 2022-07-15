package com.example.Service;

import com.example.DAO.BookDAO;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

	private BookDAO bookDAO;

	public BookServiceImpl() {
	}

	@Autowired
	public BookServiceImpl(BookDAO bookDAO) {
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

	public void addBook(String authorId, String title, int page, boolean published, int quantity, int price) {
		bookDAO.addBook(authorId, title, page, published, quantity, price);
	}

	public List<String> getBooks() {
		return bookDAO.getBooks();
	}

	public List<Book> getBooksAsBooks() {
		return bookDAO.getBooksAsBooks();
	}

	public boolean isPublished(String title) {
		return bookDAO.isPublished(title);
	}
}
