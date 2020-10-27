package com.mindtree.shoppingCartApplication.dao;

import java.util.List;


import com.mindtree.shoppingCartApplication.entity.Apparal;
import com.mindtree.shoppingCartApplication.entity.Book;
import com.mindtree.shoppingCartApplication.entity.Product;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartDaoException;


/**
 * @author M1056108
 *
 */
public interface ProductDao {

	public Product saveProduct(Product product);
	
	public void updateProduct(Product product);
	
	public List<Product> getAllProducts(Product product);
	
	public Product searchProductById(int productId) throws ShoppingCartDaoException;

	public Product searchProductByName(String productName) throws ShoppingCartDaoException;

	public List<Book> findAllBooks() throws ShoppingCartDaoException;

	public List<Apparal> findAllApparals() throws ShoppingCartDaoException;

	public boolean isProductPresent(int productId);
}
