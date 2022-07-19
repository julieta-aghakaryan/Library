package com.example.DAO;

import com.example.model.Shopping;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class ShoppingDAO extends BasicDAO<Shopping, ObjectId> {

	public ShoppingDAO() {
		super(new MongoClient(new MongoClientURI("mongodb://localhost:27017/")), new Morphia(), "library");
	}

	public ShoppingDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	public void addShopping(Shopping shopping) {
		this.save(shopping);
	}
}
