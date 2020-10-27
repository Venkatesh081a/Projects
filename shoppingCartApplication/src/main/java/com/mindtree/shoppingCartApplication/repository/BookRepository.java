package com.mindtree.shoppingCartApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCartApplication.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
