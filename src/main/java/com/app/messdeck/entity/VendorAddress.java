package com.app.messdeck.entity;

import javax.persistence.Entity;

@Entity
public class VendorAddress extends Address {

	@Override
	public String toString() {
		return "VendorAddress [id=" + id + ", Address = " + super.toString() + "]";
	}

}
