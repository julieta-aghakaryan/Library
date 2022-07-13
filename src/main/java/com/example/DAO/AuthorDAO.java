package com.example.DAO;

import com.example.Service.BookService;
import com.example.model.Author;
import com.example.model.Book;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class AuthorDAO {

	public BookService bookService = new BookService();
	public ArrayList<Author> authorMap;
	Author author1 = new Author("Dorian Grey");
	Author author2 = new Author("HovhannesTumanyan");

	Book book1 = new Book(author1, "The Picture Of The Dorian Grey", 100, true, 11);
	Book book2 = new Book(author2, "Fairy Tales", 200, false, 12);

	public AuthorDAO(ArrayList<Author> authorMap) {
		this.authorMap = authorMap;
		authorMap.add(author1);
		authorMap.add(author2);
		author1.setBooks(book1);
		author2.setBooks(book2);
	}

	public Author getAuthor(String name) {
		for (int i = 0; i < authorMap.size(); i++) {
			if (authorMap.get(i).getName().equals(name)) {
				return authorMap.get(i);
			}
		}
		throw new DuplicateRequestException("The book does not exists");
	}

	public void deleteAuthor(String authorName) {
		boolean flag = true;

		for (int i = 0; i < authorMap.size(); i++) {
			if (authorMap.get(i).getName().equals(authorName)) {
				authorMap.remove(authorMap.get(i));
				flag = false;
				break;
			}
		}
		if (flag) throw new RuntimeException("There is no such book");
	}

	public String getAuthorsBooks(String authorName) {
		for (int i = 0; i < authorMap.size(); i++) {
			if (authorMap.get(i).getName().equals(authorName)) {
				return authorMap.get(i).getBooks();
			}
		}
		throw new RuntimeException("There is no such author");
	}

	public void updateAuthorName(String authorName, String name) {
		boolean flag = true;

		for (int i = 0; i < authorMap.size(); i++) {
			if (authorMap.get(i).getName().equals(authorName)) {
				authorMap.get(i).setName(name);
				flag = false;
				break;
			}
		}
		if (flag) throw new IllegalStateException("There is no Author with name " + authorName);
	}

	public void addAuthor(String name, String title, int page, boolean published, int quantity) {
		boolean flag = true;

		for (Author value : authorMap) {
			if (value.getName().equals(name)) {
				Book book = new Book(value, title, page, published, quantity);
				value.setBooks(book);
				flag = false;
				break;
			}
		}
		if (flag) {
			Author author = new Author(name);
			Book book = new Book(author, title, page, published, quantity);
			author.setBooks(book);
			authorMap.add(author);
		}
	}

	public List<String> getAuthors() {
		List<String> list = new ArrayList<>();

		for (int i = 0; i < authorMap.size(); i++) {
			list.add(authorMap.get(i).getName());
		}
		if (list.size() == 0) {
			throw new IllegalStateException("There are no authors");
		}
		return list;
	}
}
