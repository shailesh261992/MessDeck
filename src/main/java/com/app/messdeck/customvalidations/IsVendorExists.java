package com.app.messdeck.customvalidations;

import net.sf.oval.constraint.CheckWithCheck;

public class IsVendorExists implements CheckWithCheck.SimpleCheck {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isSatisfied(Object validatedObject, Object value) {

		return false;
	}

}
