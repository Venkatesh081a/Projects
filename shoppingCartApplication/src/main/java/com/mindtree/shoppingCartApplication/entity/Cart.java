package com.mindtree.shoppingCartApplication.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author M1056108
 *
 */
@Entity
public class Cart implements Comparable<Cart> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private double cartPrice;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<Product> productsList;

	public Cart(int cartId, double cartPrice, List<Product> productsList) {
		super();
		this.cartId = cartId;
		this.cartPrice = cartPrice;
		this.productsList = productsList;
	}
	
	public Cart() {
		super();

	}
	
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}


	public List<Product> getProductsList() {
		return productsList;
	}

	
	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		long temp;
		temp = Double.doubleToLongBits(cartPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productsList == null) ? 0 : productsList.hashCode());
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
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		if (Double.doubleToLongBits(cartPrice) != Double.doubleToLongBits(other.cartPrice))
			return false;
		if (productsList == null) {
			if (other.productsList != null)
				return false;
		} else if (!productsList.equals(other.productsList))
			return false;
		return true;
	}

	public double getPrice() {

		return 0;
	}

	@Override
	public int compareTo(Cart cart) {
		if (cartId > cart.getCartId()) {
			return 1;
		} else if (cartId < cart.getCartId()) {
			return -1;
		} else {
			return 0;
		}
	}

}
