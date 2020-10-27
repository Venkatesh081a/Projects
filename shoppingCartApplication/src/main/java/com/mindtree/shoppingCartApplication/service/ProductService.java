package com.mindtree.shoppingCartApplication.service;

import java.util.List;


import com.mindtree.shoppingCartApplication.entity.Product;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartServiceException;

public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public void updateProduct(Product product);
	
	public Product searchProductById(int productId)throws ShoppingCartServiceException;
	
	public Product searchProductByName(String productName)throws ShoppingCartServiceException;
	
	public List<Product> searchProductByCategory(String productCategory)throws ShoppingCartServiceException;

	public Product getProductById(int productId)throws ShoppingCartServiceException;
	
}
