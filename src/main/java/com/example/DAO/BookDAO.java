package com.example.DAO;

import com.example.Service.AuthorService;
import com.example.model.Author;
import com.example.model.Book;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDAO {

	Author author1 = new Author("Dorian Gray");
	Author author2 = new Author("Hovhannes Tumanyan");
	Book book1 = new Book(author1, "The Picture Of The Dorian Gray", 300, true, 20);
	Book book2 = new Book(author2, "Fairy Tales", 200, false, 10);
	public List<Book> bookMap;
	public AuthorService authorService;

	public BookDAO(List<Book> bookMap) {
		this.bookMap = bookMap;
		bookMap.add(book1);
		bookMap.add(book2);
	}

	public List<Book> getBookMap() {
		return bookMap;
	}

	public Book getBook(String title) {
		for (int i = 0; i < bookMap.size(); i++) {
			if (bookMap.get(i).getTitle().equals(title)) {
				return bookMap.get(i);
			}
		}
		throw new DuplicateRequestException("The book does not exists");
	}

	public void deleteBook(String title) {
		boolean flag = true;

		for (int i = 0; i < bookMap.size(); i++) {
			if (bookMap.get(i).getTitle().equals(title)) {
				bookMap.remove(bookMap.get(i));
				flag = false;
				break;
			}
		}
		if (flag) throw new RuntimeException("There is no such book");
	}

	public void updateBookName(String bookTitle, String title) {
		boolean flag = true;

		for (Book book : bookMap) {
			if (book.getTitle().equals(bookTitle)) {
				book.setTitle(title);
				flag = false;
				break;
			}
		}
		if (flag) throw new IllegalStateException("There is no Book with title " + bookTitle);
	}

	public void addBook(String authorName, String title, int page, boolean published, int quantity) {
		Book book;
		boolean flag = true;

		for (Book value : bookMap) {
			if (value.getTitle().equals(title)) {
				flag=false;
			}
		}
		if(flag) {
			Author author = new Author(authorName);
			book = new Book(author, title, page, published, quantity);
			bookMap.add(book);
		}
	}

	public List<String> getBooks() {
		List<String> list = new ArrayList<>();

		for (Book book : bookMap) {
			String s = book.getTitle();
			list.add(s);
		}

		if (list.size() == 0) {
			throw new IllegalStateException("There are no books");
		}
		return list;
	}

	public List<Book> getBooksAsBooks() {
		List<Book> list = new ArrayList<>();

		for (int i = 0; i < bookMap.size(); i++) {
			Book book = bookMap.get(i);
			list.add(book);
		}
		return list;
	}

	public boolean isPublished(String title) {
		for (int i = 0; i < bookMap.size(); i++) {
			if (bookMap.get(i).getTitle().equals(title)) {
				return bookMap.get(i).isPublished();
			}
		}
		throw new RuntimeException("There is no such book");
	}
}
