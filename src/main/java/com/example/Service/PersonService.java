package com.example.Service;

import com.example.model.Book;
import com.example.model.Person;

import java.util.List;

public interface PersonService {
	public Person getPerson(String name);

	public void deletePerson(String personName);

	public String getPersonsBooks(String personName);

	public void updatePersonName(String personName, String name);

	public void addPerson(String name, int age, Book book);

	public List<String> getPersons();
}
