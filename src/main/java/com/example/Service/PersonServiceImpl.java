package com.example.Service;

import com.example.DAO.PersonDAO;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	@Autowired
	public PersonServiceImpl(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public void deletePerson(String personId) {
		personDAO.deletePerson(personId);
	}

	public List<String> getPersonsBooks(String personName) {
		return personDAO.getPersonsBooks(personName);
	}

	public void updatePersonName(String personId, String name) {
		personDAO.updatePersonName(personId, name);
	}

	public void addPerson(Person person) {
		personDAO.addPerson(person);
	}

	public List<String> getPersons() {
		return personDAO.getPersons();
	}
}
