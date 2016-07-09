package com.app.messdeck.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.messdeck.annotations.Loggable;
import com.app.messdeck.aspects.ValidateWithOval;
import com.app.messdeck.businessException.CustomerNotExistsException;
import com.app.messdeck.businessException.MessDeckServiceInfoNotExistException;
import com.app.messdeck.businessException.VendorNotExistException;
import com.app.messdeck.entity.Customer;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.modelmapper.DTOConverter;
import com.app.messdeck.modelmapper.EntityConverter;
import com.app.messdeck.repository.CustomerRepository;
import com.app.messdeck.repository.MessDeckServiceInfoRepository;
import com.app.messdeck.repository.VendorRepository;
import com.app.messdeck.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	// private static Logger logger =
	// Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private MessDeckServiceInfoRepository messDeckServiceRepository;

	@Override
	@Loggable
	public CustomerDTO getCustomerSummary(Long id) {

		Optional<Customer> customer = Optional.ofNullable(repository.findOne(id));
		if (customer.isPresent()) {
			return EntityConverter.getCustomorSummaryDTO(customer.get());
		} else {
			throw new CustomerNotExistsException(id);
		}

	}

	@Override
	@ValidateWithOval
	public Long createCustomer(CustomerDTO dto) {

		if (vendorRepository.exists(dto.getVendorId())) {
			Customer customer = repository.save(DTOConverter.getCustomer(dto));
			return customer.getId();
		} else {
			throw new VendorNotExistException(dto.getVendorId());
		}

	}

	@Override
	@ValidateWithOval
	public void updateCustomer(CustomerDTO dto) {
		if (repository.exists(dto.getId())) {

			if (vendorRepository.exists(dto.getVendorId())) {
				repository.save(DTOConverter.getCustomer(dto));
			} else {
				throw new VendorNotExistException(dto.getVendorId());
			}

		} else {
			throw new CustomerNotExistsException(dto.getId());
		}
	}

	@Override
	public void deleteCustomer(Long id) {
		if (repository.exists(id)) {
			repository.delete(id);
		} else {
			throw new CustomerNotExistsException(id);
		}
	}

	@Override
	public boolean subScribeMessDeckService(Long id, Long serviceId) {
		if (repository.exists(id)) {
			if (messDeckServiceRepository.exists(serviceId)) {
				return repository.getOne(id).getSubscribedServices().add(messDeckServiceRepository.getOne(serviceId));
			} else {
				throw new MessDeckServiceInfoNotExistException(id);
			}
		} else {
			throw new CustomerNotExistsException(id);
		}

	}

}
