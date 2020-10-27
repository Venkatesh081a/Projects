package com.mindtree.shoppingCartApplication.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.shoppingCartApplication.exception.ShoppingCartException;
import com.mindtree.shoppingCartApplication.utility.ExceptionHandling;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(ShoppingCartException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionHandling exceptionGetter(final ShoppingCartException exception, final HttpServletRequest request) {
		ExceptionHandling exceptionResult = new ExceptionHandling();
		exceptionResult.setMessage(exception.getMessage());
		exceptionResult.setUrl(request.getRequestURI());
		return exceptionResult;

	}

}
