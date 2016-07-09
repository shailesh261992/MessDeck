package com.app.messdeck.modelmapper;

import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.Customer;
import com.app.messdeck.model.dto.CustomerDTO;

public class CustomerMap extends PropertyMap<Customer, CustomerDTO> {

	@Override
	protected void configure() {
		map(source.getVendor().getId()).setVendorId(0l);
	}

}
