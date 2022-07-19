package com.example.Contoller;

import com.example.Service.PersonService;
import com.example.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void addPerson(@RequestParam(value = "person", required = true) String person
	) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Person person1 = objectMapper.readValue(person, Person.class);
		personServiceImpl.addPerson(person1);
	}

	@GetMapping
	public List<String> getPersons() {
		return personServiceImpl.getPersons();
	}

	@DeleteMapping(path = "{personId}")
	public void deletePerson(@PathVariable("personId") String personId) {
		personServiceImpl.deletePerson(personId);
	}

	@PutMapping(path = "{ObjectId}")
	public void updatePersonName(
			@PathVariable("ObjectId") String personId,
			@RequestParam(value = "name", required = true) String name) {

		personServiceImpl.updatePersonName(personId, name);
	}

	@GetMapping("/personsBook/{personId}")
	@ResponseBody
	public List<String> getPersonsBooks(@PathVariable String personId) {
		return personServiceImpl.getPersonsBooks(personId);
	}
}
