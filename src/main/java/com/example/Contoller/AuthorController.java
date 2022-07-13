package com.example.Contoller;

import com.example.Service.AuthorService;
import com.example.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/author")
public class AuthorController {

	private final AuthorService authorService;
	private final BookService bookService;

	@Autowired
	public AuthorController(AuthorService authorService, BookService bookService) {
		this.authorService = authorService;
		this.bookService = bookService;
	}

	@PostMapping(path = "/addAuthor")
	public void addAuthor(@RequestParam(value = "authorName", required = true) String authorName,
	                      @RequestParam(value = "title", required = true) String title,
	                      @RequestParam(value = "page", required = true) int page,
	                      @RequestParam(value = "published", required = true) boolean published,
	                      @RequestParam(value = "quantity", required = true) int quantity
	) {

		StringBuilder finalName = new StringBuilder();
		String[] array = authorName.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalName.append(array[i]).append(" ");
		}
		finalName.append(array[array.length - 1]);

		StringBuilder finalTitle = new StringBuilder();
		String[] array2 = title.split("_");

		for (int i = 0; i < array2.length - 1; i++) {
			finalTitle.append(array2[i]).append(" ");
		}
		finalTitle.append(array2[array2.length - 1]);

		authorService.addAuthor(finalName.toString(), finalTitle.toString(), page, published, quantity);
		bookService.addBook(finalName.toString(), finalTitle.toString(), page, published, quantity);
	}


	@GetMapping
	public List<String> getAuthors() {
		return authorService.getAuthors();
	}

	@DeleteMapping(path = "{authorName}")
	public void deleteAuthor(@PathVariable("authorName") String authorName) {
		String finalName = "";
		String[] array = authorName.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalName += array[i] + " ";
		}
		finalName += array[array.length - 1];
		authorService.deleteAuthor(finalName);
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

		authorService.updateAuthorName(finalFirstName.toString(), finalName.toString());
	}

	@GetMapping("/authorsBooks/{name}")
	@ResponseBody
	public String getAuthorsBooks(@PathVariable String name) {

		StringBuilder finalWord = new StringBuilder();
		String[] array = name.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalWord.append(array[i]).append(" ");
		}
		finalWord.append(array[array.length - 1]);
		return authorService.getAuthorsBooks(finalWord.toString());
	}

}
