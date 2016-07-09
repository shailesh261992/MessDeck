package com.app.messdeck.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.Customer;
import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.entity.Vendor;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.model.dto.VendorDTO;

public class EntityConverter {

	public static VendorDTO getVendorSummaryDTO(Vendor vendor) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Vendor, VendorDTO>() {

			@Override
			protected void configure() {
				skip().setOwner(null);
				skip().setCustomers(null);
				skip().setServices(null);

			}
		});

		VendorDTO dto = modelMapper.map(vendor, VendorDTO.class);
		System.out.println("VendorSummary = " + dto);
		return dto;

	}

	public static VendorDTO getVendorDetailsDTO(Vendor vendor) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Vendor, VendorDTO>() {

			@Override
			protected void configure() {
				skip().setCustomers(null);
				skip().setServices(null);

			}
		});

		VendorDTO dto = modelMapper.map(vendor, VendorDTO.class);
		System.out.println("VendorSummary = " + dto);
		return dto;

	}

	public static CustomerDTO getCustomorSummaryDTO(Customer customer) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new CustomerMap());
		return modelMapper.map(customer, CustomerDTO.class);

	}

	public static MessDeckServiceInfoDTO getMessDeckServiceInfoDTO(MessDeckServiceInfo messDeckService) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new MessDeckServiceInfoMap());
		MessDeckServiceInfoDTO dto = modelMapper.map(messDeckService, MessDeckServiceInfoDTO.class);
		return dto;
	}

}
