package com.mindtree.shoppingCartApplication.service;

import com.mindtree.shoppingCartApplication.entity.Cart;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartServiceException;

public interface CartService {
	
	public Cart insertProductsInToCart(int userId, int productId) throws ShoppingCartServiceException;
	
	public Cart deleteAProductFromCart(int userId, int productId) throws ShoppingCartServiceException;

	public String deleteAllProductsFromCart(int userId) throws ShoppingCartServiceException;
	
	public Cart updateProductQuantity(int userId, int productId, int productQuantity)
			throws ShoppingCartServiceException;
	
	public Cart viewCart(int userId) throws ShoppingCartServiceException;
	
}
