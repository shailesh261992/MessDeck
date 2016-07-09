package com.app.messdeck.model.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.sf.oval.constraint.AssertValid;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO extends PersonDTO {

	@AssertValid(message = "Invalid Customer Address")
	private CustomerAddressDTO customerAddress;

	@NotNull
	private Long vendorId;

	public CustomerDTO() {
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendor) {
		this.vendorId = vendor;
	}

	public CustomerAddressDTO getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddressDTO customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", customerAddress=" + customerAddress + ", vendor=" + vendorId + ", person"
				+ super.toString() + "]";
	}

}
