package com.example.DAO;

import com.example.model.Author;

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
public class AuthorDAO extends BasicDAO<Author, ObjectId> {

	public AuthorDAO() {
		super( new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "library" );
	}
	public AuthorDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}
	public Author getAuthor(String name) {
		Query<Author> query = this.createQuery();
		return (Author) query.filter("authorName", new ObjectId(name));
	}

	public void deleteAuthor(String authorName) {
		Query<Author> query = this.createQuery()
				.field("authorName")
				.equal(authorName);

		this.delete((Author) query);
	}

	public void updateAuthorName(String authorName, String name) {
		Query<Author> query = this.createQuery()
				.field("authorName")
				.contains(authorName);

		UpdateOperations<Author> updates = this.createUpdateOperations().inc(name);

		this.update(query, updates);
	}

	public void addAuthor(Author author) {
		this.save(author);
	}

	public List<Author> getAuthors() {
		return this.createQuery()
				.find()
				.toList();
	}

	public List<String> getAuthorsNames() {
		List<Author> authors = this.createQuery()
				.find()
				.toList();

		List<String> authorNames = new ArrayList<>();
		for (Author author : authors) {
			authorNames.add(author.getName());
		}
		return authorNames;
	}

	public ObjectId getObjectIdByAuthorName(String authorName){
		Query<Author> query = this.createQuery()
				.field("authorName")
				.contains(authorName);
		Author author = (Author) query;
		return author.getId();
	}
}
