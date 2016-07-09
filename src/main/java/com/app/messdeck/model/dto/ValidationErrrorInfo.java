package com.app.messdeck.model.dto;

public class ValidationErrrorInfo {

	private String fieldName;
	private String errorMessage;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ValidationErrrorInfo [fieldName=" + fieldName + ", errorMessage=" + errorMessage + "]";
	}

}
