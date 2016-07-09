package com.app.messdeck.businessException;

public class VendorNotExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VendorNotExistException(long id) {
		super("Vendor with id = " + id + " Not exists");
	}

}
