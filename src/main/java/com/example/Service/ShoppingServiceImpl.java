package com.example.Service;

import com.example.DAO.ShoppingDAO;
import com.example.model.Book;
import com.example.model.Person;
import com.example.model.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingServiceImpl implements  ShoppingService{

	private ShoppingDAO shoppingDAO;

	@Autowired
	public ShoppingServiceImpl(ShoppingDAO shoppingDAO) {
		this.shoppingDAO = shoppingDAO;
	}

	public void addShopping(Person person, List<Book> bookList, int totalPrice, int quantity){
		 shoppingDAO.addShopping(person, bookList, totalPrice, quantity);
	}

	public Shopping getShopping(String name){
		return shoppingDAO.getShopping(name);
	}


}
