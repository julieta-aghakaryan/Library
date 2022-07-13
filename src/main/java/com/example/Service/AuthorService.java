package com.example.Service;

import com.example.DAO.AuthorDAO;
import com.example.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

	private AuthorDAO authorDAO;

	@Autowired
	public AuthorService(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public Author getAuthor(String authorName) {
		return authorDAO.getAuthor(authorName);
	}

	public void deleteAuthor(String authorName) {
		authorDAO.deleteAuthor(authorName);
	}

	public String getAuthorsBooks(String authorName) {
		return authorDAO.getAuthorsBooks(authorName);
	}

	public void updateAuthorName(String authorName, String name) {
		authorDAO.updateAuthorName(authorName, name);
	}

	public void addAuthor(String name, String title, int page, boolean published, int quantity) {
		authorDAO.addAuthor(name, title, page, published, quantity);
	}

	public List<String> getAuthors() {
		return authorDAO.getAuthors();
	}
}
