package com.mindtree.shoppingCartApplication.dao.daoimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCartApplication.dao.ProductDao;
import com.mindtree.shoppingCartApplication.entity.Apparal;
import com.mindtree.shoppingCartApplication.entity.Book;
import com.mindtree.shoppingCartApplication.entity.Product;
import com.mindtree.shoppingCartApplication.exception.ProductNotFoundException;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartDaoException;
import com.mindtree.shoppingCartApplication.repository.ApparalRepository;
import com.mindtree.shoppingCartApplication.repository.BookRepository;
import com.mindtree.shoppingCartApplication.repository.ProductRepository;


/**
 * @author M1056108
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ApparalRepository apparalRepository;

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateProduct(Product product) {
		productRepository.saveAndFlush(product);

	}
	
	@Override
	public List<Product> getAllProducts(Product product) {
		 return productRepository.findAll();	
	}
	
	@Override
	public Product searchProductById(int productId) throws ShoppingCartDaoException {
		if (productRepository.findById(productId).isPresent()) {
			return productRepository.findById(productId).get();
		} else {
			throw new ProductNotFoundException("Product Id is not available ...");
			
		}
	}

	@Override
	public Product searchProductByName(String productName) throws ShoppingCartDaoException {
		if ((productRepository.existsByProductName(productName)) == true) {
			return productRepository.fetchByProductName(productName);
		} else {
			throw new ProductNotFoundException("Product Name is not available ...");
		}

	}

	@Override
	public List<Book> findAllBooks() throws ShoppingCartDaoException {
		if (bookRepository.findAll().isEmpty()) {
			throw new ProductNotFoundException("No Product for Book is available");
		} else {
			return  bookRepository.findAll();
		}
	}

	@Override
	public List<Apparal> findAllApparals() throws ShoppingCartDaoException {
		if (apparalRepository.findAll().isEmpty()) {
			throw new ProductNotFoundException("No Product for Apparal is available");
		} else {
			 return apparalRepository.findAll();
		}

	}

	@Override
	public boolean isProductPresent(int productId) {

		return productRepository.existsById(productId);
	}
}
