package com.mindtree.shoppingCartApplication.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingCartApplication.entity.Product;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartException;
import com.mindtree.shoppingCartApplication.service.ProductService;

/**
 * @author M1056108
 *
 */
@RestController
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * @param productId
	 * @return product
	 * @throws ShoppingCartException
	 */
	@GetMapping(value = "/product/searchProductById/{productId}")
	public Product searchProductById(@PathVariable int productId) throws ShoppingCartException {
		return productService.searchProductById(productId);
		
	}

	/**
	 * @param productName
	 * @return product
	 * @throws ShoppingCartException
	 */
	@GetMapping(value = "/product/searchProductByName/{productName}")
	public Product searchProductByName(@PathVariable String productName) throws ShoppingCartException {
		return productService.searchProductByName(productName);
	}

	/**
	 * @param productCategory
	 * @return product
	 * @throws ShoppingCartException
	 */
	@GetMapping(value = "/product/searchProductByCategory/{productCategory}")
	public List<Product> searchProductByCategory(@PathVariable String productCategory) throws ShoppingCartException {
		return productService.searchProductByCategory(productCategory);	
	}

}
