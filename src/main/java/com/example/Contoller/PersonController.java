package com.example.Contoller;

import com.example.Service.PersonService;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController {

	private final PersonService personServiceImpl;

	@Autowired
	public PersonController(PersonService personServiceImpl) {
		this.personServiceImpl = personServiceImpl;
	}

	@PostMapping(path = "/addPerson")
	public void addPerson(@RequestParam(value = "name", required = true) String name,
	                      @RequestParam(value = "age", required = true) int age,
	                      @RequestParam(value = "book", required = true) Book book
	                      ) {

		StringBuilder personName = new StringBuilder();
		String[] array2 = name.split("_");

		for (int i = 0; i < array2.length - 1; i++) {
			personName.append(array2[i]).append(" ");
		}
		personName.append(array2[array2.length - 1]);

		personServiceImpl.addPerson(personName.toString(), age, book);
	}

	@GetMapping
	public List<String> getPersons() {
		return personServiceImpl.getPersons();
	}

	@DeleteMapping(path = "{personName}")
	public void deletePerson(@PathVariable("personName") String personName) {
		personServiceImpl.deletePerson(personName);
	}

	@PutMapping(path = "{personName}")
	public void updatePersonName(
			@PathVariable("personName") String personName,
			@RequestParam(value = "name", required = true) String name) {

		StringBuilder finalFirstName = new StringBuilder();
		String[] array = personName.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalFirstName.append(array[i]).append(" ");
		}
		finalFirstName.append(array[array.length - 1]);

		StringBuilder finalName = new StringBuilder();
		String[] array1 = name.split("_");

		for (int i = 0; i < array1.length - 1; i++) {
			finalName.append(array1[i]).append(" ");
		}
		finalName.append(array1[array1.length - 1]);

		personServiceImpl.updatePersonName(finalFirstName.toString(), finalName.toString());
	}

	@GetMapping("/personsBook/{name}")
	@ResponseBody
	public String getPersonsBooks(@PathVariable String name) {

		StringBuilder finalWord = new StringBuilder();
		String[] array = name.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalWord.append(array[i]).append(" ");
		}
		finalWord.append(array[array.length - 1]);
		return personServiceImpl.getPersonsBooks(finalWord.toString());
	}
}
