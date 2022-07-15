package com.example.DAO;

import com.example.model.Author;
import com.example.model.Book;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
public class BookDAO extends BasicDAO<Book, ObjectId> {

	public BookDAO() {
		super(new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "book");
	}

	public BookDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	public List<Book> getBooksAsBooks() {
		return this.createQuery()
				.find()
				.toList();
	}

	public Book getBook(String title) {
		Query<Book> query = this.createQuery();
		return (Book) query.filter("bookTitle", new ObjectId(title));
	}

	public void deleteBook(String title) {
		Query<Book> query = this.createQuery()
				.field("bookTitle")
				.equal(title);

		this.delete((Book) query);
	}

	public void updateBookName(String bookTitle, String title) {
		Query<Book> query = this.createQuery()
				.field("bookTitle")
				.contains(bookTitle);

		UpdateOperations<Book> updates = this.createUpdateOperations().inc(title);

		this.update(query, updates);
	}

	public void addBook(String authorId, String title, int page, boolean published, int quantity, int price) {

		Book book = new Book();
		book.setTitle(title);
		book.setAuthorId(authorId);
		book.setPrice(price);
		book.setPages(page);
		book.setPublished(published);
		book.setQuantity(quantity);

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
		Book book= (Book) this.createQuery()
				.field("title")
				.contains(title);
		return book.isPublished();
	}
}
