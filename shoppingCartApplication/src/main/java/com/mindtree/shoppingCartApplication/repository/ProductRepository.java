package com.mindtree.shoppingCartApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCartApplication.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product fetchByProductName(String productName);
	
	public boolean existsByProductName(String productName);

}
