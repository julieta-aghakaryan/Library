package com.example.DAO;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Person;
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
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.*;

@Component
public class PersonDAO extends BasicDAO<Person, ObjectId> {

	public PersonDAO() {
		super( new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "author" );
	}
	public PersonDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	public Person getPerson(String name) {
		Query<Person> query = this.createQuery();
		return (Person) query.filter("personName", new ObjectId(name));
	}

	public void deletePerson(String personName) {
		Query<Person> query = this.createQuery()
				.field("personName")
				.equal(personName);

		this.delete((Person) query);	}

	public void updatePersonName(String personName, String name) {
		Query<Person> query = this.createQuery()
				.field("personName")
				.contains(personName);

		UpdateOperations<Person> updates = this.createUpdateOperations().inc(name);

		this.update(query, updates);	}

	public void addPerson(String name, int age, Book book) {
		Person person = new Person();
		person.setName(name);
		person.setAge(age);
		person.setBooks(book);

		this.save(person);

	}

	public List<String> getPersons() {
		List<Person> people = this.createQuery()
				.find()
				.toList();

		List<String> peopleNames = new ArrayList<>();
		for (Person person : people) {
			peopleNames.add(person.getName());
		}
		return peopleNames;
	}

	public String getPersonsBooks(String personName) {
		Person person= (Person) this.createQuery()
				.field("personName")
				.contains(personName);
		return person.getBooks();	}
}

