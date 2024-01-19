package com.demo.servise;

import com.demo.model.Book;

public interface BookService {

	Integer createBook(Book book);
	Book findByIsbn(Integer isbn);
}
