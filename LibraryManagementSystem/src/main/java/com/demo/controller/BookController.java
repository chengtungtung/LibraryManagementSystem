package com.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Book;
import com.demo.model.Inventory;
import com.demo.servise.BookService;
import com.demo.servise.InventoryService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/create")
	public ResponseEntity<Book> register(@RequestBody Book book) {
		Date now = new Date();
		Inventory inventory = new Inventory();
		inventory.setStoreTime(now);
		inventory.setStatus("在庫");
		Integer inventoryId = inventoryService.createInventory(inventory);
		book.setInventory(inventory);
		Integer isbn = bookService.createBook(book);
		Book createdBook = bookService.findByIsbn(isbn);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
	}

}
