package com.app.messdeck.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Customer extends Person {

	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;

	@ManyToOne
	private Vendor vendor;

	@ManyToMany
	@JoinTable(name = "CustomerService", joinColumns = { @JoinColumn(name = "subscriber") }, inverseJoinColumns = {
			@JoinColumn(name = "mess_deck_service") })
	private Set<MessDeckServiceInfo> subscribedServices;

	public Customer() {
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Set<MessDeckServiceInfo> getSubscribedServices() {
		return subscribedServices;
	}

	public void setSubscribedServices(Set<MessDeckServiceInfo> subscribedServices) {
		this.subscribedServices = subscribedServices;
	}

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerAddress=" + customerAddress + ", vendor=" + vendor + ", person="
				+ super.toString() + "]";
	}

}
