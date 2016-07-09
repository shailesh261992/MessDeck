package com.app.messdeck.businessException;

public class MessDeckServiceInfoNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessDeckServiceInfoNotExistException(long id) {
		super("MessDeckService with id = " + id + " Not exists");
	}

}
