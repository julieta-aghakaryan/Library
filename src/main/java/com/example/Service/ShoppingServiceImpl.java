package com.example.Service;

import com.example.DAO.ShoppingDAO;
import com.example.model.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingServiceImpl implements ShoppingService {

	private ShoppingDAO shoppingDAO;

	@Autowired
	public ShoppingServiceImpl(ShoppingDAO shoppingDAO) {
		this.shoppingDAO = shoppingDAO;
	}

	public void addShopping(Shopping shopping) {
		shoppingDAO.addShopping(shopping);
	}
}
