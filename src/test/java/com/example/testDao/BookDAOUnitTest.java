//package com.example.testDao;
//
//import com.example.DAO.AuthorDAO;
//import com.example.DAO.BookDAO;
//import com.example.config.EmbeddedMongoDB;
//import com.example.model.Author;
//import com.example.model.Book;
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
//public class BookDAOUnitTest {
//    BookDAO testBookDAO;
//    static Book book;
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
//        testBookDAO = new BookDAO(
//                embeddedMongoDB.getClient(),
//                new Morphia(),
//                "mydb"
//        );
//
//        book= new Book();
//        book.setId(new ObjectId("62bad031b4cd4b1326879c0e"));
//        Author author = new Author();
//        author.setId(new ObjectId("62bad031b4cd4b1326879c0f"));
//        author.setName("Nar Dos");
//        book.setAuthorId("62bad031b4cd4b1326879c0f");
//        book.setPages(233);
//        book.setPrice(100);
//        book.setPublished(false);
//        book.setTitle("Spanvats Aghavni");
//        book.setQuantity(30);
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
//    public void testGetBook(){
//        Book book1 = testBookDAO.getBook("62bad031b4cd4b1326879c0e");
//        assertEquals(book1, book);
//    }
//
//    @Test
//    public void testAddBook(){
//        testBookDAO.addBook(book);
//        assertEquals(testBookDAO.getBook("62bad031b4cd4b1326879c0e"), book);
//    }
//
//    @Test
//    public void testDeleteBook(){
//
//        testBookDAO.addBook(book);
//        testBookDAO.deleteBook("62bad031b4cd4b1326879c0e");
//        assertNotNull(testBookDAO.getBook("62bad031b4cd4b1326879c0e"));
//    }
//
//    @Test
//    public void testUpdateBookTitle(){
//
//        String newName = "New Book";
//        testBookDAO.updateBookName("62bad031b4cd4b1326879c0e", newName);
//        assertEquals(testBookDAO.getBook("62bad031b4cd4b1326879c0e"),newName);
//    }
//
//    @Test
//    public void testGetBooksNames() {
//        Author author2 = new Author();
//        author2.setId(new ObjectId("62bad031b4cd4b1326879c0q"));
//        author2.setName("Ana");
//        Book book2 = new Book();
//        book2.setAuthorId("62bad031b4cd4b1326879c0e");
//        book2.setPages(190);
//        book2.setPrice(70);
//        book2.setPublished(true);
//        book2.setTitle("Fairy tale");
//        book2.setQuantity(20);
//        testBookDAO.addBook(book2);
//        testBookDAO.addBook(book);
//        List<String> titles = testBookDAO.getBooks();
//        List<String> list = new ArrayList<>();
//        list.add("Fairy tale");
//        list.add("Spanvats Aghavni");
//        assertEquals(list, titles);
//    }
//
//}