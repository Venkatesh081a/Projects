package com.mindtree.shoppingCartApplication.dao;

import com.mindtree.shoppingCartApplication.entity.User;


/**
 * @author M1056108
 *
 */
public interface UserDao {

	public User addUser(User user);
	
	public void updateUser(User user);
	
	public User getUserById(int userId);

	public boolean isUserPresent(int userId);

}
