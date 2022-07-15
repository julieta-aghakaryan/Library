package com.example.DAO;

import com.example.model.Book;
import com.example.model.Person;
import com.example.model.Shopping;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;
import dev.morphia.query.Query;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ShoppingDAO extends BasicDAO<Shopping, ObjectId> {

public ShoppingDAO() {
		super( new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "shopping" );
		}
public ShoppingDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
		}

public void addShopping(Person person, List<Book> bookList, int totalPrice, int quantity){
	Shopping shopping = new Shopping();
	shopping.setPerson(person);
	shopping.setBooks(bookList);
	shopping.setTotalPrice(totalPrice);

	this.save(shopping);
	}

	public Shopping getShopping(String name){
		Query<Shopping> query = this.createQuery();
		return (Shopping) query.filter("personName", new ObjectId(name));
	}
}
