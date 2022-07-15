package com.example.Contoller;

import com.example.Service.BookService;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping(path = "/api/book")
public class BookController {

	private final BookService bookServiceImpl;


	@Autowired
	public BookController(BookService bookServiceImpl){
		this.bookServiceImpl = bookServiceImpl;
	}

	@PostMapping(path = "/addBook")
	public void addBook(@RequestParam(value = "authorId", required = true) String authorId,
	                    @RequestParam(value = "title", required = true) String title,
	                    @RequestParam(value = "page", required = true) int page,
	                    @RequestParam(value = "published", required = true) boolean published,
	                    @RequestParam(value = "quantity", required = true) int quantity,
	                    @RequestParam(value = "price", required = true) int price
	                    ) {
		StringBuilder finalTitle = new StringBuilder();
		String[] array = title.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalTitle.append(array[i]).append(" ");
		}
		finalTitle.append(array[array.length - 1]);
		bookServiceImpl.addBook(authorId, finalTitle.toString(), page, published, quantity, price);

	}

	@GetMapping
	public List<String> getBooks() {
		return bookServiceImpl.getBooks();
	}

	@DeleteMapping(path = "{title}")
	public void deleteBook(@PathVariable("title") String title) {
		StringBuilder finalTitle = new StringBuilder();
		String[] array = title.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalTitle.append(array[i]).append(" ");
		}
		finalTitle.append(array[array.length - 1]);
		bookServiceImpl.deleteBook(finalTitle.toString());
	}

	@PutMapping(path = "{bookTitle}")
	public void updateBookName(
			@PathVariable("bookTitle") String bookTitle,
			@RequestParam(value = "title", required = true) String title) {

		StringBuilder finalFirstTitle = new StringBuilder();
		String[] array2 = bookTitle.split("_");

		for (int i = 0; i < array2.length - 1; i++) {
			finalFirstTitle.append(array2[i]).append(" ");
		}
		finalFirstTitle.append(array2[array2.length - 1]);


		StringBuilder finalTitle = new StringBuilder();
		String[] array = title.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalTitle.append(array[i]).append(" ");
		}
		finalTitle.append(array[array.length - 1]);
		bookServiceImpl.updateBookName(finalFirstTitle.toString(), finalTitle.toString());
	}

	@GetMapping("/isPublished/{title}")
	@ResponseBody
	public boolean isPublished(@PathVariable String title) {
		StringBuilder finalWord = new StringBuilder();
		String[] array = title.split("_");

		for (int i = 0; i < array.length - 1; i++) {
			finalWord.append(array[i]).append(" ");
		}
		finalWord.append(array[array.length - 1]);

		return bookServiceImpl.getBook(finalWord.toString()).isPublished();
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
