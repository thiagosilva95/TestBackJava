package com.thiagodev.app.categoryservice.exception;

public class CategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5496341680698981027L;

	public CategoryNotFoundException(final String exception) {
		super(exception);
	}
}
