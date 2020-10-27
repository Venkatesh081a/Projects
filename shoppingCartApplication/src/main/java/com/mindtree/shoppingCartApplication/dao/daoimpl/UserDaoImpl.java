package com.mindtree.shoppingCartApplication.dao.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCartApplication.dao.UserDao;
import com.mindtree.shoppingCartApplication.entity.User;
import com.mindtree.shoppingCartApplication.repository.UserRepository;


/**
 * @author M1056108
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = false)
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId).get();
	}
	
	@Override
	public boolean isUserPresent(int userId) {
		return userRepository.existsById(userId);
	}

	

	

}
