package com.mindtree.shoppingCartApplication.dao.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mindtree.shoppingCartApplication.dao.CartDao;
import com.mindtree.shoppingCartApplication.entity.Cart;
import com.mindtree.shoppingCartApplication.repository.CartRepository;


/**
 * @author M1056108
 *
 */
@Service
public class CartDaoImpl implements CartDao {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public void createCart(Cart cart) {
		cartRepository.save(cart);

	}
	
	@Override
	public void updateCart(Cart cart) {
		cartRepository.saveAndFlush(cart);
	}

	@Override
	public Cart getCartById(int cartId) {

		return cartRepository.findById(cartId).get();
	}

	@Override
	public String deleteCartById(int cartId) {
		cartRepository.deleteById(cartId);
		return "Deleted Successfully";

	}

}
