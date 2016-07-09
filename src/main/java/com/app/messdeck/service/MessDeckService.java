package com.app.messdeck.service;

import java.util.List;

import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;

public interface MessDeckService {

	MessDeckServiceInfoDTO createMessDeckService(MessDeckServiceInfoDTO messDeckServiceDTO);

	MessDeckServiceInfoDTO getMessDeckService(Long id);

	MessDeckServiceInfoDTO updateMessDeckService(MessDeckServiceInfoDTO messDeckServiceDTO);

	void deleteMessDeckService(Long id);

	List<CustomerDTO> getSubscribedCustomers(Long id);

}
