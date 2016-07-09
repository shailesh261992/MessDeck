package com.app.messdeck.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

@JsonInclude(Include.NON_EMPTY)
public class VendorDTO extends AbstractDTO {

	@MatchPattern(pattern = "^[a-zA-Z ]*$", message = "Only Alphabets are allowed")
	@NotNull
	private String name;

	@AssertValid(message = "Invalid Vendor Address")
	private VendorAddressDTO vendorAddress;

	@AssertValid(message = "Invalid Owner")
	private OwnerDTO owner;

	@AssertValid
	private EmailIDDTO emailID;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime registrationDate;

	private List<CustomerDTO> customers;

	private List<MessDeckServiceInfoDTO> services;

	public VendorDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}

	public List<MessDeckServiceInfoDTO> getServices() {
		return services;
	}

	public void setServices(List<MessDeckServiceInfoDTO> services) {
		this.services = services;
	}

	public VendorAddressDTO getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(VendorAddressDTO vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public List<CustomerDTO> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}

	public EmailIDDTO getEmailID() {
		return emailID;
	}

	public void setEmailID(EmailIDDTO emailID) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendorDTO other = (VendorDTO) obj;
		if (emailID == null) {
			if (other.emailID != null)
				return false;
		} else if (!emailID.equals(other.emailID))
			return false;
		return true;
	}

}
