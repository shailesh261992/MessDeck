package com.app.messdeck.entity;

import javax.persistence.Entity;

@Entity
public class OwnerAddress extends Address {

	@Override
	public String toString() {
		return "OwnerAddress [id=" + id + ", Address=" + super.toString() + "]";
	}

}
