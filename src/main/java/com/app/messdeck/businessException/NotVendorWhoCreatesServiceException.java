package com.app.messdeck.businessException;

import com.app.messdeck.entity.MessDeckServiceInfo;

public class NotVendorWhoCreatesServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotVendorWhoCreatesServiceException(MessDeckServiceInfo s) {
		super("Vendor with id : " + s.getVendor().getId() + "can not update MessDeckService with id : " + s.getId());
	}

}
