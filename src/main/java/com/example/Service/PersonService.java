package com.example.Service;

import com.example.DAO.PersonDAO;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

	private PersonDAO personDAO;

	@Autowired
	public PersonService(PersonDAO personDAO) {
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

	public void addPerson(String name, int age, String title, int page, String authorName, boolean published, int quantity) {
		personDAO.addPerson(name, age, title, page, authorName, published, quantity);
	}

	public List<String> getPersons() {
		return personDAO.getPersons();
	}
}
