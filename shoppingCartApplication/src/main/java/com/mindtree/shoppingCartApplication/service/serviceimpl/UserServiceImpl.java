package com.mindtree.shoppingCartApplication.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCartApplication.dao.UserDao;
import com.mindtree.shoppingCartApplication.entity.User;
import com.mindtree.shoppingCartApplication.exception.UserNotFoundException;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartServiceException;
import com.mindtree.shoppingCartApplication.service.UserService;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User addUser(User user) {
		return userDao.addUser(user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUser(User user) {
		userDao.updateUser(user);

	}
	
	@Override
	public User getUserById(int userId) throws ShoppingCartServiceException {
		if (userDao.isUserPresent(userId)) {
			return userDao.getUserById(userId);
		}
		else
		{
			throw new UserNotFoundException("User Not Found");
		}
	}
}
