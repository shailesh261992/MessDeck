package com.app.messdeck.model.dto;

public class VendorAddressDTO extends AddressDTO {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VendorAddress [id=" + id + ", Address = " + super.toString() + "]";
	}

}
