package com.example.Service;

import com.example.DAO.PersonDAO;
import com.example.model.Book;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

	private PersonDAO personDAO;

	@Autowired
	public PersonServiceImpl(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public Person getPerson(String name) {
		return personDAO.getPerson(name);
	}

	public void deletePerson(String personName) {
		personDAO.deletePerson(personName);
	}

	public String getPersonsBooks(String personName) {
		return personDAO.getPersonsBooks(personName);
	}

		public void updatePersonName(String personName, String name) {
		personDAO.updatePersonName(personName, name);
	}

	public void addPerson(String name, int age, Book book) {
		personDAO.addPerson(name, age, book);
	}

	public List<String> getPersons() {
		return personDAO.getPersons();
	}
}
