package com.example.DAO;

import com.example.model.Book;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDAO extends BasicDAO<Book, ObjectId> {

	public BookDAO() {
		super(new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "library");
	}

	public BookDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	public List<Book> getBooksAsBooks() {
		return this.createQuery()
				.find()
				.toList();
	}

	public Book getBook(String bookId) {
		return this.find()
				.field("_id")
				.equal(new ObjectId(bookId)).get();
	}

	public void deleteBook(String bookId) {
		this.deleteByQuery(this.createQuery()
				.field("_id")
				.equal(new ObjectId(bookId)));
	}

	public void updateBookName(String bookId, String title) {
		Query<Book> query = this.createQuery()
				.field("_id")
				.equal(new ObjectId(bookId));

		UpdateOperations<Book> updates = this.createUpdateOperations().set("title", title);
		this.update(query, updates);
	}

	public void addBook(Book book) {
		this.save(book);
	}

	public List<String> getBooks() {
		List<Book> books = this.createQuery()
				.find()
				.toList();

		List<String> bookTitles = new ArrayList<>();
		for (Book book : books) {
			bookTitles.add(book.getTitle());
		}
		return bookTitles;
	}

	public boolean isPublished(String title) {
		Book book = (Book) this.createQuery()
				.field("title")
				.contains(title);
		return book.isPublished();
	}

	public String getBooksNames() {
		List<Book> books = this.createQuery()
				.find()
				.toList();
		String bookNames = "";
		if (books.size() > 1) {
			for (int i = 0; i < books.size() - 1; i++) {
				bookNames += books.get(i).getTitle() + ", ";
			}
			bookNames += books.get(books.size() - 1);
			return bookNames;
		}
		return books.get(books.size() - 1).getTitle();
	}
}
