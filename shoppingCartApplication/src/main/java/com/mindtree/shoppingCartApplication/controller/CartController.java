package com.mindtree.shoppingCartApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mindtree.shoppingCartApplication.entity.Cart;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartException;
import com.mindtree.shoppingCartApplication.service.CartService;

/**
 * @author M1056108
 *
 */

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	/**
	 * @param userId
	 * @param productId
	 * @return
	 * @throws ShoppingCartException
	 */
	@PostMapping(value = "/cart/addProducts/{userId}/{productId}")
	public Cart insertProductsInToCart(@PathVariable int userId, @PathVariable int productId) throws ShoppingCartException {
		return cartService.insertProductsInToCart(userId, productId);
	}
	

	
	/**
	 * @param userId
	 * @param productId
	 * @return
	 * @throws ShoppingCartException
	 */
	@DeleteMapping(value = "/cart/deleteParticularProduct/{userId}/{productId}")
	public Cart deleteAProductFromCart(@PathVariable int userId, @PathVariable int productId) throws ShoppingCartException {
		return cartService.deleteAProductFromCart(userId, productId);
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws ShoppingCartException
	 */
	@DeleteMapping(value = "/cart/deleteAllProducts/{userId}")
	public String deleteAllProductsFromCart(@PathVariable int userId) throws ShoppingCartException {
		return cartService.deleteAllProductsFromCart(userId);
	}

	/**
	 * @param userId
	 * @param productId
	 * @param productQuantity
	 * @return
	 * @throws ShoppingCartException
	 */
	@PutMapping(value  ="/cart/updateProductQunatity/{userId}/{productId}/{productQuantity}")
	public Cart updateProductQuantity(@PathVariable int userId,@PathVariable int productId, @PathVariable int productQuantity) throws ShoppingCartException {
		return cartService.updateProductQuantity(userId, productId, productQuantity);
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws ShoppingCartException
	 */
	@GetMapping(value = "cart/viewCart/{userId}")
	public Cart viewCart(@PathVariable int userId) throws ShoppingCartException {
		return cartService.viewCart(userId);	
	}
	
}
