package com.example.Contoller;

import com.example.Service.AuthorService;
import com.example.model.Author;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Author author1 = objectMapper.readValue(author, Author.class);
		authorServiceImpl.addAuthor(author1);
	}

	@GetMapping(path = "/names")
	public List<String> getAuthorsNames() {
		return authorServiceImpl.getAuthorsNames();
	}

	@GetMapping(path = "/id/{authorId}")
	public String getAuthor(@PathVariable("authorId") String authorId) {
		return authorServiceImpl.getAuthor(authorId);
	}

	@DeleteMapping(path = "{authorId}")
	public void deleteAuthor(@PathVariable("authorId") String authorId) {

		authorServiceImpl.deleteAuthor(authorId);
	}

	@PutMapping(path = "{authorId}")
	public void updateAuthorName(
			@PathVariable("authorId") String authorId,
			@RequestParam(value = "name", required = true) String name) {

		authorServiceImpl.updateAuthorName(authorId, name);
	}
}
