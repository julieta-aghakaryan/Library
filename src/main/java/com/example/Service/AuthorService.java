package com.example.Service;

import com.example.model.Author;
import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorService {
	String getAuthor(String authorId);

	void deleteAuthor(String authorId);

	void updateAuthorName(String authorId, String name);

	void addAuthor(Author author);

	public List<String> getAuthorsNames();

	public ObjectId getObjectIdByAuthorName(String authorName);
}
