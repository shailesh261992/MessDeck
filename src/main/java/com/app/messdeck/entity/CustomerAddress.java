package com.app.messdeck.entity;

import javax.persistence.Entity;

@Entity
public class CustomerAddress extends Address {


	@Override
	public String toString() {
		return "CustomerAddress [id=" + id + ", address=" + super.toString() + "]";
	}

}
