package com.example.testService;

import com.example.DAO.AuthorDAO;
import com.example.Service.AuthorServiceImpl;
import com.example.model.Author;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorServiceUnitTest {

    @Mock
    AuthorDAO testAuthorDAO;
    AuthorServiceImpl authorService;
    static Author author;

    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(testAuthorDAO);

        author = new Author();
        author.setId(new ObjectId("62b9c462fcb4e65a98b68afd"));
        author.setName("Mike");
    }

    @Test
    void getAuthorTest() {
        String name = testAuthorDAO.getAuthor("62d521fa5233f2374a1b5241");
        assertEquals("Nane", name);
        when(testAuthorDAO.getAuthor("62d521fa5233f2374a1b5241")).thenReturn("Mike");
        assertEquals(testAuthorDAO.getAuthor("62d521fa5233f2374a1b5241"), authorService.getAuthor("62d521fa5233f2374a1b5241"));
    }

}