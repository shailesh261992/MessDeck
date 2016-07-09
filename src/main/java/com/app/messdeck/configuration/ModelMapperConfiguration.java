package com.app.messdeck.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.messdeck.entity.Vendor;
import com.app.messdeck.model.dto.VendorDTO;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		PropertyMap<Vendor, VendorDTO> vendorSummary = new PropertyMap<Vendor, VendorDTO>() {

			@Override
			protected void configure() {
				skip().setOwner(null);

			}
		};

		modelMapper.addMappings(vendorSummary);

		return modelMapper;
	}

	
}
