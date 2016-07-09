package com.app.messdeck.model.dto;

public class OwnerAddressDTO extends AddressDTO {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OwnerAddress [id=" + id + ", Address=" + super.toString() + "]";
	}

}
