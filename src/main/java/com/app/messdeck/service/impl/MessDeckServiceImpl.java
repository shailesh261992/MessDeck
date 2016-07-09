package com.app.messdeck.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.messdeck.aspects.ValidateWithOval;
import com.app.messdeck.businessException.MessDeckServiceInfoNotExistException;
import com.app.messdeck.businessException.VendorNotExistException;
import com.app.messdeck.entity.Customer;
import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.modelmapper.DTOConverter;
import com.app.messdeck.modelmapper.EntityConverter;
import com.app.messdeck.repository.MessDeckServiceInfoRepository;
import com.app.messdeck.repository.VendorRepository;
import com.app.messdeck.service.MessDeckService;

@Service
@Transactional
public class MessDeckServiceImpl implements MessDeckService {

	@Autowired
	private MessDeckServiceInfoRepository repository;

	@Autowired
	private VendorRepository vendorRepository;

	@Override
	@ValidateWithOval
	public MessDeckServiceInfoDTO createMessDeckService(MessDeckServiceInfoDTO messDeckServiceDTO) {
		MessDeckServiceInfo messDeckServiceInfo;
		if (vendorRepository.exists(messDeckServiceDTO.getVendorId())) {
			messDeckServiceInfo = repository.save(DTOConverter.getMessDeckServiceInfo(messDeckServiceDTO));
			return EntityConverter.getMessDeckServiceInfoDTO(messDeckServiceInfo);
		} else {
			throw new VendorNotExistException(messDeckServiceDTO.getVendorId());
		}

	}

	@Override
	public MessDeckServiceInfoDTO getMessDeckService(Long id) {
		Optional<MessDeckServiceInfo> messDeckServiceInfo = Optional.ofNullable(repository.findOne(id));
		if (messDeckServiceInfo.isPresent()) {
			return EntityConverter.getMessDeckServiceInfoDTO(messDeckServiceInfo.get());

		} else {
			throw new MessDeckServiceInfoNotExistException(id);
		}

	}

	@Override
	@ValidateWithOval
	public MessDeckServiceInfoDTO updateMessDeckService(MessDeckServiceInfoDTO messDeckServiceDTO) {
		if (repository.exists(messDeckServiceDTO.getId())) {
			MessDeckServiceInfo messDeckServiceInfo = repository.getOne(messDeckServiceDTO.getId());
			if (messDeckServiceDTO.getVendorId() == messDeckServiceInfo.getVendor().getId()) {
				return EntityConverter.getMessDeckServiceInfoDTO(
						repository.save(DTOConverter.getMessDeckServiceInfo(messDeckServiceDTO)));
			} else {
				throw new RuntimeException("Not allowed to update vendor of service vendor should be with id = "
						+ messDeckServiceInfo.getVendor().getId());
			}
		} else {
			throw new MessDeckServiceInfoNotExistException(messDeckServiceDTO.getId());
		}

	}

	@Override
	public void deleteMessDeckService(Long id) {
		if (repository.exists(id)) {
			repository.delete(id);
		} else {
			throw new MessDeckServiceInfoNotExistException(id);
		}

	}

	@Override
	public List<CustomerDTO> getSubscribedCustomers(Long id) {
		if (repository.exists(id)) {
			MessDeckServiceInfo messDeckService = repository.getOne(id);
			List<CustomerDTO> subscribers = messDeckService.getSubscribers().stream()
					.map(x -> EntityConverter.getCustomorSummaryDTO(x)).collect(Collectors.toList());
			return subscribers;

		} else {
			throw new MessDeckServiceInfoNotExistException(id);
		}

	}

}
