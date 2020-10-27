package com.mindtree.shoppingCartApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingCartApplication.entity.User;
import com.mindtree.shoppingCartApplication.service.UserService;


/**
 * @author M1056108
 */
@RestController
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/insertUser")
	public User addUser(@RequestBody  User user ) {
		return userService.addUser(user);
	}
}
