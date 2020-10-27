package com.mindtree.shoppingCartApplication.service.serviceimpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCartApplication.dao.CartDao;
import com.mindtree.shoppingCartApplication.entity.Cart;
import com.mindtree.shoppingCartApplication.entity.Product;
import com.mindtree.shoppingCartApplication.entity.User;
import com.mindtree.shoppingCartApplication.exception.CartNotFoundException;
import com.mindtree.shoppingCartApplication.exception.NotAbleToDeleteException;
import com.mindtree.shoppingCartApplication.exception.UserNotFoundException;
import com.mindtree.shoppingCartApplication.exception.ProductNotFoundException;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartServiceException;
import com.mindtree.shoppingCartApplication.service.CartService;
import com.mindtree.shoppingCartApplication.service.ProductService;
import com.mindtree.shoppingCartApplication.service.UserService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Cart insertProductsInToCart(int userId, int productId) throws ShoppingCartServiceException {
		User user = userService.getUserById(userId);
		Product product = productService.getProductById(productId);
		Cart cart;

		if (user.getCart() != null) 
		{
			cart = cartDao.getCartById(user.getCart().getCartId());
			cart.setCartPrice(cart.getCartPrice() + product.getPrice());
			cartDao.updateCart(cart);
			product.setQuantity(product.getQuantity() + 1);
			product.setCart(cart);
			productService.updateProduct(product);
		} 
		else 
		{
			cart = new Cart();
			user.setCart(cart);
			cart.setCartPrice(cart.getCartPrice() + product.getPrice());
			cartDao.createCart(cart);
			product.setCart(cart);
			product.setQuantity(1);
			productService.updateProduct(product);
		}
		if ((product.getCart() == null) || (user.getCart() != product.getCart()))
		{
			throw new ProductNotFoundException("Product is not available");
		}
		return cart;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Cart deleteAProductFromCart(int userId, int productId) throws ShoppingCartServiceException {
		User user = userService.getUserById(userId);
		Product product = productService.getProductById(productId);
		Cart cart = cartDao.getCartById(user.getCart().getCartId());
		
		if (cart == null) {
			throw new CartNotFoundException("Product is not available as the specified user has no relation with cart");
		}
		if ((product.getCart() == null) || (product.getCart().getCartId() != cart.getCartId())) {
			throw new ProductNotFoundException("Product is not available  for the specified User");
		}
		double totalProductPrice = product.getQuantity() * product.getPrice();
		cart.setCartPrice(cart.getCartPrice() - totalProductPrice);
		cartDao.updateCart(cart);
		product.setCart(null);
		product.setQuantity(0);
		productService.updateProduct(product);
		productService.saveProduct(product);
		return cart;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteAllProductsFromCart(int userId) throws ShoppingCartServiceException {
		User user = userService.getUserById(userId);
		int cartId = user.getCart().getCartId();
		Cart cart = user.getCart();
		
		if (user.getUserId() != userId) {
			throw new UserNotFoundException("User is not  available ...");
		}
		if (cart == null) {
			throw new CartNotFoundException("Cart is empty ...");
		}

		List<Product> productsList = cart.getProductsList();
		for (Product product : productsList) {
			product.setCart(null);
			product.setQuantity(0);
			productService.saveProduct(product);
		}
		cart.setCartPrice(0);
		user.setCart(null);
		userService.updateUser(user);
		cartDao.deleteCartById(cartId);
		return "Items Removed Successfully from the cart";

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Cart updateProductQuantity(int userId, int productId, int productQuantity)
			throws ShoppingCartServiceException {
		User user = userService.getUserById(userId);
		Product product = productService.getProductById(productId);
		Cart cart = cartDao.getCartById(user.getCart().getCartId());
		int cartId = user.getCart().getCartId();
		double totalProductPrice;
		
		if (!(product.getCart().getCartId() == cart.getCartId())) {
			throw new ProductNotFoundException("Product is not Available ...");
		}
		if (productQuantity == 0) {
			totalProductPrice = product.getQuantity() * product.getPrice();
			cart.setCartPrice(cart.getCartPrice() - totalProductPrice);
			product.setCart(null);
			product.setQuantity(0);
			productService.updateProduct(product);

		} else {
			totalProductPrice = product.getPrice() * productQuantity;
			cart.setCartPrice(cart.getPrice() + totalProductPrice );
			product.setQuantity(product.getQuantity() + productQuantity );
			if (product.getQuantity() == 0) {
				product.setCart(null);
			}

			if (product.getQuantity() < 0) {
				throw new NotAbleToDeleteException("Product Quantity Updation Minimun limit is +1");
			}
			productService.updateProduct(product);

			if (cart.getCartPrice() == 0) {
				user.setCart(null);
				userService.updateUser(user);
				cart.setProductsList(null);
				cartDao.deleteCartById(cartId);
			}
		}
		return cart;
	}

	@Override
	public Cart viewCart(int userId) throws ShoppingCartServiceException {
		User user = userService.getUserById(userId);
		Cart cart = user.getCart();
		return cart;
	}

}
