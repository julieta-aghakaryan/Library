package com.example.Contoller;

import com.example.Service.*;
import com.example.model.Shopping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public void addShopping(@RequestParam(value = "shopping", required = true) String shopping
	) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Shopping shopping1 = objectMapper.readValue(shopping, Shopping.class);
		shoppingServiceImpl.addShopping(shopping1);
	}
}
