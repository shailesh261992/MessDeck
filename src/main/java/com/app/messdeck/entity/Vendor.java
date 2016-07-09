package com.app.messdeck.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.app.messdeck.serializer.LocalDateTimeAttributeConverter;

@Entity
public class Vendor extends AbstractEntity {

	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	private VendorAddress vendorAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private Owner owner;

	@OneToMany(mappedBy = "vendor")
	private List<Customer> customers;

	@OneToMany(mappedBy = "vendor")
	private List<MessDeckServiceInfo> services;

	@Embedded
	private EmailID emailID;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime registrationDate;

	public Vendor() {
		this.registrationDate = LocalDateTime.now();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@OneToMany(mappedBy = "vendor")
	public List<MessDeckServiceInfo> getServices() {
		return services;
	}

	public void setServices(List<MessDeckServiceInfo> services) {
		this.services = services;
	}

	public VendorAddress getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(VendorAddress vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public EmailID getEmailID() {
		return emailID;
	}

	public void setEmailID(EmailID emailID) {
		this.emailID = emailID;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", vendorAddress=" + vendorAddress + ", emailID=" + emailID
				+ ", registrationDate=" + registrationDate + "]";
	}

}
