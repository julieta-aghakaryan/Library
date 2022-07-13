package com.example.Contoller;

import com.example.Service.AuthorService;
import com.example.Service.BookService;
import com.example.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController {

	private final PersonService personService;
	private final AuthorService authorService;
	private final BookService bookService;

	@Autowired
	public PersonController(PersonService personService, AuthorService authorService, BookService bookService) {
		this.personService = personService;
		this.authorService = authorService;
		this.bookService = bookService;
	}

	@PostMapping(path = "/addPerson")
	public void addPerson(@RequestParam(value = "name", required = true) String name,
	                      @RequestParam(value = "age", required = true) int age,
	                      @RequestParam(value = "title", required = true) String title,
	                      @RequestParam(value = "page", required = true) int page,
	                      @RequestParam(value = "authorName", required = true) String authorName,
	                      @RequestParam(value = "published", required = true) boolean published,
	                      @RequestParam(value = "quantity", required = true) int quantity) {


		StringBuilder finalName = new StringBuilder();
		String[] array = authorName.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalName.append(array[i]).append(" ");
		}
		finalName.append(array[array.length - 1]);

		StringBuilder personName = new StringBuilder();
		String[] array2 = name.split("_");

		for (int i = 0; i < array2.length - 1; i++) {
			personName.append(array2[i]).append(" ");
		}
		personName.append(array2[array2.length - 1]);

		StringBuilder finalTitle = new StringBuilder();
		String[] array3 = title.split("_");

		for (int i = 0; i < array3.length - 1; i++) {
			finalTitle.append(array3[i]).append(" ");
		}
		finalTitle.append(array3[array3.length - 1]);

		personService.addPerson(personName.toString(), age, finalTitle.toString(), page, finalName.toString(), published, quantity);
		bookService.addBook(finalName.toString(), finalTitle.toString(), page, published, quantity);
		authorService.addAuthor(finalName.toString(), finalTitle.toString(), page, published, quantity);
	}

	@GetMapping
	public List<String> getPersons() {
		return personService.getPersons();
	}

	@DeleteMapping(path = "{personName}")
	public void deletePerson(@PathVariable("personName") String personName) {
		personService.deletePerson(personName);
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

		personService.updatePersonName(finalFirstName.toString(), finalName.toString());
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
		return personService.getPersonsBooks(finalWord.toString());
	}
}
