package com.app.messdeck.businessException;

import java.util.List;

import com.app.messdeck.model.dto.ValidationErrrorInfo;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ValidationErrrorInfo> violationList;

	public ValidationException(List<ValidationErrrorInfo> violationList) {
		super();
		this.violationList = violationList;
	}

	public List<ValidationErrrorInfo> getViolationList() {
		return violationList;
	}

	public void setViolationList(List<ValidationErrrorInfo> violationList) {
		this.violationList = violationList;
	}

	@Override
	public String toString() {
		return "ValidationException [violationList=" + violationList + "]";
	}

}
