package com.app.messdeck.service;

import java.util.List;

import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;

public interface CustomerService {

	CustomerDTO getCustomerSummary(Long id);

	Long createCustomer(CustomerDTO dto);

	void updateCustomer(CustomerDTO dto);

	void deleteCustomer(Long id);

	boolean subScribeMessDeckService(Long id, Long serviceId);

	boolean unSubScribeMessDeckService(Long id, Long serviceId);

	List<MessDeckServiceInfoDTO> getListOfSubscribedServices(Long id);

}
