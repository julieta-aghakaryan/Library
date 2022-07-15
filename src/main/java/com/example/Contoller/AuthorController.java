package com.example.Contoller;

import com.example.Service.AuthorService;
import com.example.model.Author;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/author")
public class AuthorController {

	private final AuthorService authorServiceImpl;


	@Autowired
	public AuthorController(AuthorService authorServiceImpl) {
		this.authorServiceImpl = authorServiceImpl;

	}

	@PostMapping(path = "/addAuthor")
	public void addAuthor(@RequestParam(value = "author", required = true) String author
	)  throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Author author1 = objectMapper.readValue(author, Author.class);
		authorServiceImpl.addAuthor(author1);

	}
	@GetMapping(path ="/names")
	public List<String> getAuthorsNames() {
		return authorServiceImpl.getAuthorsNames();
	}

	@PutMapping(path ="/id/{name}")
	public ObjectId getAuthorIdByName(@PathVariable("name") String name,
	                                  @RequestParam(value = "authorName", required = true) String authorName) {
		return authorServiceImpl.getObjectIdByAuthorName(authorName);
	}

	@DeleteMapping(path = "{authorName}")
	public void deleteAuthor(@PathVariable("authorName") String authorName) {
		String finalName = "";
		String[] array = authorName.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalName += array[i] + " ";
		}
		finalName += array[array.length - 1];
		authorServiceImpl.deleteAuthor(finalName);
	}

	@PutMapping(path = "{authorName}")
	public void updateAuthorName(
			@PathVariable("authorName") String authorName,
			@RequestParam(value = "name", required = true) String name) {

		String finalFirstName = "";
		String[] array = authorName.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalFirstName += array[i] + " ";
		}
		finalFirstName += array[array.length - 1];

		String finalName = "";
		String[] array2 = name.split("_");

		for (int i = 0; i < array2.length - 1; i++) {
			finalName += array2[i] + " ";
		}
		finalName += array2[array2.length - 1];

		authorServiceImpl.updateAuthorName(finalFirstName.toString(), finalName.toString());
	}
}
