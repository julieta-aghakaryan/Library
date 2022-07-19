package com.example.Service;

import com.example.model.Person;

import java.util.List;

public interface PersonService {

	public void deletePerson(String personId);

	public List<String> getPersonsBooks(String personName);

	public void updatePersonName(String personId, String name);

	public void addPerson(Person person);

	public List<String> getPersons();
}
