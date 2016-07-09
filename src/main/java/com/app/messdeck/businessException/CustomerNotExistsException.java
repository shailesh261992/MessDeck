package com.app.messdeck.businessException;

public class CustomerNotExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomerNotExistsException(long id) {
		super("Customer with id = " + id + " Not exists");
	}

}
