package com.app.messdeck.model.dto;

public class CustomerAddressDTO extends AddressDTO {

	@Override
	public String toString() {
		return "CustomerAddress [id=" + id + ", address=" + super.toString() + "]";
	}
}
