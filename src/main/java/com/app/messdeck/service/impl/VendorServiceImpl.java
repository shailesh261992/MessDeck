package com.app.messdeck.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.messdeck.aspects.ValidateWithOval;
import com.app.messdeck.businessException.VendorNotExistException;
import com.app.messdeck.entity.Customer;
import com.app.messdeck.entity.Vendor;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.VendorDTO;
import com.app.messdeck.modelmapper.DTOConverter;
import com.app.messdeck.modelmapper.EntityConverter;
import com.app.messdeck.repository.VendorRepository;
import com.app.messdeck.service.VendorService;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepository repository;

	public VendorDTO getVendorSummary(Long id) throws VendorNotExistException {

		Optional<Vendor> vendor = Optional.ofNullable(repository.findOne(id));

		if (vendor.isPresent()) {
			return EntityConverter.getVendorSummaryDTO(vendor.get());
		} else {
			throw new VendorNotExistException(id);
		}

	}

	@Override
	public VendorDTO getVendorDetails(Long id) throws VendorNotExistException {

		Optional<Vendor> vendor = Optional.ofNullable(repository.findOne(id));

		if (vendor.isPresent()) {
			return EntityConverter.getVendorDetailsDTO(vendor.get());
		} else {
			throw new VendorNotExistException(id);
		}

		// Vendor vendor = dao.get(id);
		//
		// return EntityConverter.getVendorDetailsDTO(vendor);

	}

	@Override
	@ValidateWithOval
	public Long createVendor(VendorDTO vendorDTO) {
		Vendor vendor = DTOConverter.getVendor(vendorDTO);
		vendor.setRegistrationDate(LocalDateTime.now());
		Vendor savedVendor = repository.save(vendor);
		return savedVendor.getId();

	}

	@Override
	@ValidateWithOval
	public void updateVendor(VendorDTO dto) {

		boolean exists = repository.exists(dto.getId());
		if (exists) {
			repository.save(DTOConverter.getVendor(dto));
		} else {
			throw new VendorNotExistException(dto.getId());
		}

	}

	@Override
	public void deleteVendor(Long id) {

		boolean exists = repository.exists(id);
		if (exists) {
			repository.delete(id);
		} else {
			throw new VendorNotExistException(id);
		}

	}

	@Override
	public List<VendorDTO> getAllVendorsSummary() {
		Optional<List<Vendor>> vendors = Optional.ofNullable(repository.findAll());
		if (vendors.isPresent()) {
			List<VendorDTO> dtoList = vendors.get().stream().map(x -> EntityConverter.getVendorSummaryDTO(x))
					.collect(Collectors.toList());
			return dtoList;
		}
		return null;
	}

	@Override
	public List<CustomerDTO> getCustomrs(Long id) {
		Optional<Vendor> vendor = Optional.ofNullable(repository.findOne(id));

		if (vendor.isPresent()) {
			Optional<List<Customer>> customers = Optional.ofNullable(vendor.get().getCustomers());
			if (customers.isPresent()) {
				return customers.get().stream().map(x -> EntityConverter.getCustomorSummaryDTO(x))
						.collect(Collectors.toList());
			}
		} else {
			throw new VendorNotExistException(id);
		}

		return null;
	}

}
