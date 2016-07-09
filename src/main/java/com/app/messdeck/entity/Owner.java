package com.app.messdeck.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Owner extends Person {
	

	@OneToOne(cascade = CascadeType.ALL)
	private OwnerAddress ownerAddress;

	public Owner() {
		this.ownerAddress = new OwnerAddress();
	}

	public OwnerAddress getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(OwnerAddress ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", ownerAddress=" + ownerAddress + ", person" + super.toString() + "]";
	}

	

}
