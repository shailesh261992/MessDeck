package com.app.messdeck.test.data;

import com.app.messdeck.entity.Gender;
import com.app.messdeck.model.dto.CustomerDTO;

public class CustomerDTODataSample {

	public static CustomerDTO getCustomerDTO() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerAddress(CustomerAddressDTODataSample.getCustomerAddressDTO());
		customerDTO.setEmailID(EmailIDDTODataSample.getEmailIDDTO_CustomerEmailID());
		customerDTO.setGender(Gender.MALE);
		customerDTO.setMobileNo("7645454550");
		customerDTO.setName(NameDTODataSample.getNameDTO_Customer());
		customerDTO.setVendorId(1l);
		return customerDTO;

	}

}
