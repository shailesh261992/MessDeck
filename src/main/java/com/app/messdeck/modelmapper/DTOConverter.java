package com.app.messdeck.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.Customer;
import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.entity.Vendor;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.model.dto.VendorDTO;

public class DTOConverter {
	// private static Logger logger = Logger.getLogger(DTOConverter.class);

	public static Vendor getVendor(VendorDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<VendorDTO, Vendor>() {

			@Override
			protected void configure() {
				skip().setCustomers(null);
				skip().setServices(null);

			}
		});

		return modelMapper.map(dto, Vendor.class);
	}

	public static Customer getCustomer(CustomerDTO dto) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new CustomerDTOMap());

		return modelMapper.map(dto, Customer.class);

	}

	public static MessDeckServiceInfo getMessDeckServiceInfo(MessDeckServiceInfoDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new MessDeckServiceDTOMap());
		modelMapper.addMappings(new ItemDTOMap());
		return modelMapper.map(dto, MessDeckServiceInfo.class);
	}

}
