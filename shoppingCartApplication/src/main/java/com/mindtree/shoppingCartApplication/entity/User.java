package com.mindtree.shoppingCartApplication.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author M1056108
 *
 */
@Entity
public class User implements Comparable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;
	private String userName;
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	public User(int userId, String userName, Cart cart) {
		super();
		UserId = userId;
		this.userName = userName;
		this.cart = cart;
	}
	
	public User() {
		super();

	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@JsonIgnore
	public Cart getCart() {
		return cart;
	}

	@JsonIgnore
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + UserId;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (UserId != other.UserId)
			return false;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public int compareTo(User user) 
	{
		if (UserId > user.getUserId()) 
		{
			return 1;
		} else if (UserId < user.getUserId()) 
		{
			return -1;
		} else 
		{
			return 0;
		}
	}

}
