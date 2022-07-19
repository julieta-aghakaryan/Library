package com.example.Contoller;

import com.example.Service.BookService;
import com.example.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/book")
public class BookController {

	private final BookService bookServiceImpl;

	@Autowired
	public BookController(BookService bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

	@PostMapping(path = "/addBook")
	public void addBook(@RequestParam(value = "book", required = true) String book)
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Book book1 = objectMapper.readValue(book, Book.class);
		bookServiceImpl.addBook(book1);
	}

	@GetMapping
	public List<Book> getBooks() {
		return bookServiceImpl.getBooksAsBooks();
	}

	@DeleteMapping(path = "{bookId}")
	public void deleteBook(@PathVariable("bookId") String bookId) {

		bookServiceImpl.deleteBook(bookId);
	}

	@PutMapping(path = "{bookId}")
	public void updateBookName(
			@PathVariable("bookId") String bookId,
			@RequestParam(value = "title", required = true) String title) {

		bookServiceImpl.updateBookName(bookId, title);
	}

	@GetMapping("/isPublished/{bookId}")
	@ResponseBody
	public boolean isPublished(@PathVariable String bookId) {
		return bookServiceImpl.getBook(bookId).isPublished();
	}

	@GetMapping("/includeUnPublished/{includeUnPublished}")
	@ResponseBody
	public List<Book> includeUnPublished(@PathVariable boolean includeUnPublished) {
		if (includeUnPublished) {
			return bookServiceImpl.getBooksAsBooks();
		}
		List<Book> listOfBooks = bookServiceImpl.getBooksAsBooks();
		List<Book> list = new ArrayList<>();
		for (int i = 0; i < listOfBooks.size(); i++) {
			if (!listOfBooks.get(i).isPublished()) {
				list.add(listOfBooks.get(i));
			}
		}
		return list;
	}
}
