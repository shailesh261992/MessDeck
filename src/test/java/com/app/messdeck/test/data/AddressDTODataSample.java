package com.app.messdeck.test.data;

import com.app.messdeck.model.dto.AddressDTO;

public class AddressDTODataSample {
	
	public static AddressDTO getAddressDTO(){
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("pune");
		addressDTO.setCountry("india");
		addressDTO.setState("maharashtra");
		addressDTO.setPinCode("410407");
		
		return addressDTO;
		
	}

}
