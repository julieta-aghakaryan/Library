package com.example.testDAO;

import com.example.DAO.AuthorDAO;
import com.example.model.Author;
import com.example.testDAO.config.EmbeddedMongoDB;
import com.mongodb.MongoClient;
import dev.morphia.Morphia;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class AuthorDAOUnitTest {

	@Autowired
	 static AuthorDAO testAuthorDAO;
	 static Author author;
	 static EmbeddedMongoDB embeddedMongoDB;

	@BeforeAll
	static void setUp() throws IOException {
		embeddedMongoDB = new EmbeddedMongoDB();
		MongoClient mongoClient = embeddedMongoDB.getClient();

		testAuthorDAO = new AuthorDAO(
				mongoClient,
				new Morphia(),
				"mydb"
		);
		embeddedMongoDB.start();

		author= new Author();
		author.setId(new ObjectId("62bad031b4cd4b1326879c0e"));
		author.setName("author name");
	}

	@AfterEach
	void drop() {
		embeddedMongoDB.getClient().dropDatabase("mydb");
	}

	@AfterAll
	static void close() {
		embeddedMongoDB.getClient().dropDatabase("mydb");
		embeddedMongoDB.stop();
	}

	@Test
	public void testGetAuthor(){
		String name = testAuthorDAO.getAuthor("62d521fa5233f2374a1b5241");
		assertEquals("Nane", name);
	}

	@Test
	public void testAddAuthor(){
		ObjectId id = new ObjectId();
		author.setId(id);
		author.setName("Mike");
		testAuthorDAO.addAuthor(author);
		assertEquals(testAuthorDAO.getObjectIdByAuthorName(author.getName()), id);
	}

	@Test
	public void testDeleteAuthor(){
		ObjectId id = new ObjectId();
		author.setId(id);
		author.setName("Mike");
		testAuthorDAO.addAuthor(author);
		testAuthorDAO.deleteAuthor(id.toString());
		assertNotNull(testAuthorDAO.getAuthor(id.toString()));
	}

	@Test
	public void testUpdateAuthorName(){
		ObjectId id = new ObjectId();
		author.setId(id);
		author.setName("Mike");
		String newName = "Ana";
		testAuthorDAO.updateAuthorName(id.toString(), newName);
		assertEquals(testAuthorDAO.getAuthor(id.toString()),newName);
	}

	@Test
	public void testGetAuthorsNames() {
		ObjectId id = new ObjectId();
		author.setId(id);
		author.setName("Mike");
		Author author2 = new Author();
		ObjectId id2 = new ObjectId();
		author2.setId(id2);
		author2.setName("Ana");
		testAuthorDAO.addAuthor(author);
		testAuthorDAO.addAuthor(author2);
		List<String> names = testAuthorDAO.getAuthorsNames();
		List<String> list = new ArrayList<>();
		list.add("Mike");
		list.add("Ana");
		assertEquals(list, names);
	}

	@Test
	public void testGetObjectIdByAuthorName(){
		ObjectId id = new ObjectId();
		author.setId(id);
		author.setName("Mike");
		ObjectId authorId = testAuthorDAO.getObjectIdByAuthorName(author.getName());
		assertEquals(authorId, id);

	}
}
