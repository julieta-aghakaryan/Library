package com.example.Service;

import com.example.DAO.BookDAO;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private BookDAO bookDAO;

	public BookServiceImpl() {
	}

	@Autowired
	public BookServiceImpl(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public Book getBook(String bookId) {
		return bookDAO.getBook(bookId);
	}

	public void deleteBook(String bookId) {
		bookDAO.deleteBook(bookId);
	}

	public void updateBookName(String bookId, String title) {
		bookDAO.updateBookName(bookId, title);
	}

	public void addBook(Book book) {
		bookDAO.addBook(book);
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

	@Override
	public String getBooksNames() {
		return bookDAO.getBooksNames();
	}
}
