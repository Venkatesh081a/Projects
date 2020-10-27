package com.mindtree.shoppingCartApplication.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCartApplication.dao.ProductDao;
import com.mindtree.shoppingCartApplication.entity.Apparal;
import com.mindtree.shoppingCartApplication.entity.Book;
import com.mindtree.shoppingCartApplication.entity.Product;
import com.mindtree.shoppingCartApplication.exception.ProductNotFoundException;
import com.mindtree.shoppingCartApplication.exception.ShoppingCartServiceException;
import com.mindtree.shoppingCartApplication.service.ProductService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class productServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product saveProduct(Product product) {
		return productDao.saveProduct(product);
	}
	
	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);

	}
	
	@Override
	public Product searchProductById(int productId) throws ShoppingCartServiceException {
		if (productDao.isProductPresent(productId)) {
			return productDao.searchProductById(productId);
		}
		throw new ProductNotFoundException("Product is not availble ...");
	}

	@Override
	public Product searchProductByName(String productName) throws ShoppingCartServiceException {
		 return productDao.searchProductByName(productName);
	}

	@Override
	public List<Product> searchProductByCategory(String productCategory) throws ShoppingCartServiceException {
		List<Product> productsList = new ArrayList<Product>();
		if (productCategory.equalsIgnoreCase("Book")) {
			List<Book> booksList = productDao.findAllBooks();
			if (booksList.size() == 0) {
				throw new ProductNotFoundException("Product Category is not avaialble ..");
			}
			booksList.stream().forEach(book -> productsList.add(book));
		} 
		else if (productCategory.equalsIgnoreCase("Apparal")) {
			List<Apparal> apparalsList = productDao.findAllApparals();
			if (apparalsList.size() == 0) {
				throw new ProductNotFoundException("Product Category is not avaialble ..");
			}
			apparalsList.stream().forEach(apparal -> productsList.add(apparal));
		} else {
			throw new ProductNotFoundException("Product Category is not avaialble ..");
		}
		return productsList;

	}

	@Override
	public Product getProductById(int productId) throws ShoppingCartServiceException {
		if (productDao.isProductPresent(productId)) {
			return productDao.searchProductById(productId);
		}
		else
		{
			throw new ProductNotFoundException("Product is not available ...");
		}
		
	}

	
}
