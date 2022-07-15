package com.example.Service;

import com.example.model.Author;
import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorService {
	Author getAuthor(String authorName);
	void deleteAuthor(String authorName);
	void updateAuthorName(String authorName, String name);
	void addAuthor(Author author);
	public List<String> getAuthorsNames();
	public ObjectId getObjectIdByAuthorName(String authorName);
}
