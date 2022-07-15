package com.example.Service;

import com.example.model.Book;
import com.example.model.Person;
import com.example.model.Shopping;

import java.util.List;

public interface ShoppingService {
	public void addShopping(Person person, List<Book> bookList, int totalPrice, int quantity);

	public Shopping getShopping(String name);
}
