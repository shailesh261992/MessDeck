package com.app.messdeck.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.entity.Customer;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.modelmapper.DTOConverter;
import com.app.messdeck.repository.CustomerRepository;
import com.app.messdeck.test.data.CustomerDTODataSample;

public class UnitTestCustomerServiceImpl extends AbstractUnitTest {

	// @Mock
	// private CustomerRepository repository;
	//
	//
	//
	// @InjectMocks
	// private CustomerServiceImpl service;
	//
	// CustomerDTO dto;
	//
	// {
	// dto = CustomerDTODataSample.getCustomerDTO();
	// }
	//
	// @Before
	// public void setUp() throws Exception {
	//
	// MockitoAnnotations.initMocks(this);
	//
	// }
	//
	// @Test
	// public void testCreateCustomer() {
	// when(repository.save(any(Customer.class))).thenReturn(DTOConverter.getCustomer(dto));
	// Long customerID = service.createCustomer(dto);
	// System.out.println("custome id = " + customerID);
	//
	// }

	// @Test(expected = ValidationException.class)
	// public void testCreateCustomerWithInvalidData() {
	// when(daoMock.create(any(Customer.class))).thenReturn(1l);
	// CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
	// customerDTO.setMobileNo("123");
	// repository.createCustomer(customerDTO);
	//
	// }
	//
	// @Test(expected = Exception.class)
	// public void testCreateCustomerWithNonExistingVendor() {
	// when(daoMock.create(any(Customer.class))).thenThrow(new Exception());
	// CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
	// customerDTO.setVendor(new VendorDTO());
	// repository.createCustomer(customerDTO);
	//
	// }
	//
	// @Test
	// public void testGetCustomer() {
	// when(daoMock.get(1)).thenReturn(DTOConverter.getCustomer(CustomerDTODataSample.getCustomerDTO()));
	// CustomerDTO customerDTO = repository.getCustomerSummary(1l);
	// assertEquals(CustomerDTODataSample.getCustomerDTO().getName(),
	// customerDTO.getName());
	// }
	//
	// @Test(expected = CustomerNotExistsException.class)
	// public void testGetNonExistingCustomer() {
	// when(daoMock.get(1)).thenThrow(CustomerNotExistsException.class);
	// repository.getCustomerSummary(1l);
	//
	// }
	//
	// @Test()
	// public void testDeleteCustomer() {
	// when(daoMock.get(1)).thenReturn(DTOConverter.getCustomer(CustomerDTODataSample.getCustomerDTO()));
	// doNothing().when(daoMock).delete(any(Customer.class));
	// repository.deleteCustomer(1l);
	// verify(daoMock).get(1);
	// verify(daoMock).delete(any(Customer.class));
	//
	// }
	//
	// @Test(expected = CustomerNotExistsException.class)
	// public void testDeleteNonExistingCustomer() {
	// when(daoMock.get(1)).thenThrow(CustomerNotExistsException.class);
	// repository.deleteCustomer(1l);
	//
	// }
	//
	// @Test()
	// public void testUpdateCustomer() {
	// CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
	// customerDTO.setId(1);
	// customerDTO.setMobileNo("9876598765");
	// doNothing().when(daoMock).update(DTOConverter.getCustomer(customerDTO));
	//
	// }
	//
	// @Test(expected = CustomerNotExistsException.class)
	// public void testUpdateNonExistingCustomer() {
	// CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
	// customerDTO.setId(1);
	// customerDTO.setMobileNo("9876598765");
	// doThrow(CustomerNotExistsException.class).when(daoMock).update(DTOConverter.getCustomer(customerDTO));
	// repository.updateCustomer(customerDTO);
	//
	// }
	//
	// @Test(expected = VendorNotExistException.class)
	// public void testUpdateNonExistingVendorReferredByCustomer() {
	// CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
	// customerDTO.setId(1);
	// doThrow(VendorNotExistException.class).when(daoMock).update(DTOConverter.getCustomer(customerDTO));
	// repository.updateCustomer(customerDTO);
	//
	// }
}
