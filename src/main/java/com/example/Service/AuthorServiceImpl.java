package com.example.Service;

import com.example.DAO.AuthorDAO;
import com.example.model.Author;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorDAO authorDAO;

	@Autowired
	public AuthorServiceImpl(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public String getAuthor(String objectId) {
		return authorDAO.getAuthor(objectId);
	}

	public void deleteAuthor(String authorId) {
		authorDAO.deleteAuthor(authorId);
	}

	public void updateAuthorName(String authorId, String name) {
		authorDAO.updateAuthorName(authorId, name);
	}

	public void addAuthor(Author author) {
		authorDAO.addAuthor(author);
	}

	public List<String> getAuthorsNames() {
		return authorDAO.getAuthorsNames();
	}

	public ObjectId getObjectIdByAuthorName(String authorName) {
		return authorDAO.getObjectIdByAuthorName(authorName);
	}
}
