package com.mindtree.shoppingCartApplication.service;

import com.mindtree.shoppingCartApplication.entity.User;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartServiceException;

public interface UserService {

	public User addUser(User user);
	
	public void updateUser(User user);
	
	public User getUserById(int userId) throws ShoppingCartServiceException;

}
