package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByIsbn(Integer isbn);
	Book findByName(String name);
}
