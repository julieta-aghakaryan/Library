package com.example.DAO;

import com.example.model.Person;
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
public class PersonDAO extends BasicDAO<Person, ObjectId> {

	public PersonDAO() {
		super( new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "library" );
	}
	public PersonDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	public Person getPerson(String name) {
		Query<Person> query = this.createQuery();
		return (Person) query.filter("personName", new ObjectId(name));
	}

	public void deletePerson(String personId) {
		this.deleteByQuery(this.createQuery()
				.field("_id")
				.equal(new ObjectId(personId)));
	}

	public void updatePersonName(String personId, String name) {
		Query<Person> query = this.createQuery()
				.field("_id")
				.equal(new ObjectId(personId));

		UpdateOperations<Person> updates = this.createUpdateOperations().set("name", name);
		this.update(query, updates);	}

	public void addPerson(Person person) {
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

	public List<String> getPersonsBooks(String personName) {
		Query<Person> person= this.createQuery()
				.field("personName")
				.equal(personName);
		return person.first().getBooks();	}
}

