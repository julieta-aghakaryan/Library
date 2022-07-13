package com.example.DAO;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Person;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonDAO {

	Person person1 = new Person("Julieta", 12);
	Person person2 = new Person("Ani", 99);
	public List<Person> personMap;

	public PersonDAO(List<Person> personMap) {
		this.personMap = personMap;
		personMap.add(person1);
		personMap.add(person2);
	}

	public Person getPerson(String name) {
		for (int i = 0; i < personMap.size(); i++) {
			if (personMap.get(i).getName().equals(name)) {
				return personMap.get(i);
			}
		}
		throw new DuplicateRequestException("The person does not exists");
	}

	public void deletePerson(String personName) {
		boolean flag = true;

		for (int i = 0; i < personMap.size(); i++) {
			if (personMap.get(i).getName().equals(personName)) {
				personMap.remove(personMap.get(i));
				flag = false;
				break;
			}
		}
		if (flag) throw new RuntimeException("There is no such person");
	}

	public void updatePersonName(String personName, String name) {
		boolean flag = true;

		for (Person person : personMap) {
			if (person.getName().equals(personName)) {
				person.setName(name);
				flag = false;
				break;
			}
		}
		if (flag) throw new IllegalStateException("There is no person with name " + personName);
	}

	public void addPerson(String name, int age, String title, int page, String authorName, boolean published, int quantity) {

		boolean flag = true;

		for (Person value : personMap) {
			if (value.getName().equals(name)) {
				Author author= new Author(authorName);
				Book book = new Book(author, title, page, published, quantity);
				value.setBooks(book);
				flag = false;
				break;
			}
		}
		if (flag) {
			Person person1 = new Person(name,age);
			Book book = new Book(new Author(authorName), title, page, published, quantity);
			person1.setBooks(book);
			personMap.add(person1);
		}
	}

	public List<String> getPersons() {
		List<String> list = new ArrayList<>();

		for (Person person : personMap) {
			String s = person.getName();
			list.add(s);
		}

		if (list.size() == 0) {
			throw new IllegalStateException("There are no people");
		}
		return list;
	}

	public String getPersonsBooks(String personName) {
		for (Person person : personMap) {
			if (person.getName().equals(personName)) {
				return person.getBooks();
			}
		}
		throw new RuntimeException("There is no such author");
	}
}
