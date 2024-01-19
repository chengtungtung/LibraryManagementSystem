package com.demo.servise.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.demo.model.Book;
import com.demo.model.User;
import com.demo.repository.BookRepository;
import com.demo.servise.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Integer createBook(Book book) {
		// 檢查書是否登記過
		Book checkBook = bookRepository.findByIsbn(book.getIsbn());
		if (checkBook == null) {
			bookRepository.save(book);
			log.info("{} 已成功登記", book.getName());
			return book.getIsbn();
		} else {
			log.warn("{} 已被登記過 ", book.getName());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public Book findByIsbn(Integer isbn) {

		return bookRepository.findByIsbn(isbn);
	}

}
