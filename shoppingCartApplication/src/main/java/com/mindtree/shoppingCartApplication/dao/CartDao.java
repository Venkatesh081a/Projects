package com.mindtree.shoppingCartApplication.dao;

import com.mindtree.shoppingCartApplication.entity.Cart;

/**
 * @author M1056108
 *
 */
public interface CartDao {

	public void createCart(Cart cart);
	
	public void updateCart(Cart cart);

	public Cart getCartById(int cartId);

	public String deleteCartById(int cartId);

}
