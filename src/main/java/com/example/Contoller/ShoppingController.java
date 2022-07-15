package com.example.Contoller;

import com.example.Service.*;
import com.example.model.Book;
import com.example.model.Person;
import com.example.model.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/shopping")
public class ShoppingController {

	private ShoppingService shoppingServiceImpl;
	private PersonService personServiceImpl;
	private BookService bookServiceImpl;

	@Autowired
	public ShoppingController(ShoppingService shoppingServiceImpl, PersonService personServiceImpl, BookService bookServiceImpl) {
		this.shoppingServiceImpl = shoppingServiceImpl;
		this.personServiceImpl = personServiceImpl;
		this.bookServiceImpl = bookServiceImpl;
	}

	@PostMapping(path = "/addShopping")
	public void addBook(@RequestParam(value = "person", required = true) Person person,
	                    @RequestParam(value = "list", required = true) List<Book> list
	) {
		Person person1;
		int totalFinalPrice=0;
		person1 = personServiceImpl.getPerson(person.getName());
		if(person1==null){
			throw new RuntimeException("There is no such person");
		}
		List<Book> finalBookList=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Book book ;
			book = bookServiceImpl.getBook(list.get(i).getTitle());
			if(book!=null){
				finalBookList.add(book);
				totalFinalPrice += book.getPrice()*1;
			}
		}
		shoppingServiceImpl.addShopping(person, list, totalFinalPrice, 1);
	}

	@GetMapping("/getShopping/{name}")
	@ResponseBody
	public Shopping getShopping(@PathVariable String name) {

		return shoppingServiceImpl.getShopping(name);
	}

}
