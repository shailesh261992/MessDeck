package com.app.messdeck.modelmapper;

import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.Customer;
import com.app.messdeck.model.dto.CustomerDTO;

public class CustomerDTOMap extends PropertyMap<CustomerDTO, Customer> {

	@Override
	protected void configure() {
		map(source.getVendorId()).setVendor(null);

	}

}
