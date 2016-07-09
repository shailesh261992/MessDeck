package com.app.messdeck.entity;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Address extends AbstractEntity {
	@Basic
	private String street;
	@Basic
	private String city;
	@Basic
	private String state;
	@Basic
	private String country;
	@Basic
	private String pinCode;

	public Address() {
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", pinCode=" + pinCode + "]";
	}

}
