package com.app.messdeck.test.data;

import com.app.messdeck.model.dto.CustomerAddressDTO;

public class CustomerAddressDTODataSample {

	public static CustomerAddressDTO getCustomerAddressDTO() {
		CustomerAddressDTO dto = new CustomerAddressDTO();
		dto.setCity("pune");
		dto.setState("Maharashtra");
		dto.setCountry("India");
		dto.setPinCode("410507");
		dto.setStreet("Talegaon Street 1");
		return dto;

	}

}
