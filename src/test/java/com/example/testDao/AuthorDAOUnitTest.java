//package com.example.testDao;
//
//import com.example.DAO.AuthorDAO;
//import com.example.config.EmbeddedMongoDB;
//import com.example.model.Author;
//import dev.morphia.Morphia;
//import org.bson.types.ObjectId;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class AuthorDAOUnitTest {
//    AuthorDAO testAuthorDAO;
//    static Author author;
//    static EmbeddedMongoDB embeddedMongoDB;
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//        embeddedMongoDB = new EmbeddedMongoDB();
//        embeddedMongoDB.start();
//    }
//
//    @Before
//    public void setUp() throws IOException {
//        testAuthorDAO = new AuthorDAO(
//                embeddedMongoDB.getClient(),
//                new Morphia(),
//                "mydb"
//        );
//
//        author= new Author();
//        author.setId(new ObjectId("62bad031b4cd4b1326879c0e"));
//        author.setName("author name");
//    }
//
//    @AfterEach
//    void drop() {
//        embeddedMongoDB.getClient().dropDatabase("mydb");
//    }
//
//    @AfterAll
//    static void close() {
//        embeddedMongoDB.getClient().dropDatabase("mydb");
//        embeddedMongoDB.stop();
//    }
//
//
//    @Test
//    public void testGetAuthor(){
//        String name = testAuthorDAO.getAuthor("62d521fa5233f2374a1b5241");
//        assertEquals("Nane", name);
//    }
//
//    @Test
//    public void testAddAuthor(){
//        ObjectId id = new ObjectId();
//        author.setId(id)
//        ;
//        author.setName("Mike");
//        testAuthorDAO.addAuthor(author);
//        assertEquals(testAuthorDAO.getObjectIdByAuthorName(author.getName()), id);
//    }
//
//    @Test
//    public void testDeleteAuthor(){
//
//        testAuthorDAO.addAuthor(author);
//        testAuthorDAO.deleteAuthor("62bad031b4cd4b1326879c0e");
//        assertNotNull(testAuthorDAO.getAuthor("62bad031b4cd4b1326879c0e"));
//    }
//
//    @Test
//    public void testUpdateAuthorName(){
//        ObjectId id = new ObjectId();
//        author.setId(id)
//        ;
//        author.setName("Mike");
//        String newName = "Ana";
//        testAuthorDAO.updateAuthorName(id.toString(), newName);
//        assertEquals(testAuthorDAO.getAuthor(id.toString()),newName);
//    }
//
//    @Test
//    public void testGetAuthorsNames() {
//        ObjectId id = new ObjectId();
//        author.setId(id)
//        ;
//        author.setName("Mike");
//        Author author2 = new Author();
//        ObjectId id2 = new ObjectId();
//        author2.setId(id2);
//        author2.setName("Ana");
//        testAuthorDAO.addAuthor(author);
//        testAuthorDAO.addAuthor(author2);
//        List<String> names = testAuthorDAO.getAuthorsNames();
//        List<String> list = new ArrayList<>();
//        list.add("Mike");
//        list.add("Ana");
//        assertEquals(list, names);
//    }
//
//    @Test
//    public void testGetObjectIdByAuthorName(){
//        ObjectId id = new ObjectId();
//        author.setId(id)
//        ;
//        author.setName("Mike");
//        ObjectId authorId = testAuthorDAO.getObjectIdByAuthorName(author.getName());
//        assertEquals(authorId, id);
//    }
//}