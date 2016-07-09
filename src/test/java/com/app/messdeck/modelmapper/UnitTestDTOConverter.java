package com.app.messdeck.modelmapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
import org.junit.Test;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.entity.Customer;
import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.test.data.CustomerDTODataSample;
import com.app.messdeck.test.data.MessDeckServiceInfoDataSample;

public class UnitTestDTOConverter extends AbstractUnitTest {

	@Test
	public void testMessDeckServiceInfoDTOConversion() {

		MessDeckServiceInfoDTO messDeckServiceInfoDTO = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
		messDeckServiceInfoDTO.setVendor(11l);
		MessDeckServiceInfo messDeckServiceInfo = DTOConverter.getMessDeckServiceInfo(messDeckServiceInfoDTO);

		assertThat(messDeckServiceInfo, is(notNullValue()));
		assertThat(messDeckServiceInfo.getVendor(), is(notNullValue()));
		assertThat(messDeckServiceInfo.getVendor().getId(), is(11l));
		assertThat(messDeckServiceInfo.getMeal(), is(notNullValue()));
		assertThat(messDeckServiceInfo.getMeal().get(0).getId(), equalTo(0l));
	}

	@Test
	public void testCustomerDTO_To_Customer() {

		CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
		customerDTO.setId(1);
		customerDTO.setVendorId(11l);

		System.out.println("Customer DTO : " + customerDTO);

		Customer customer = DTOConverter.getCustomer(customerDTO);
		System.out.println("**** " + customer);
		assertThat(customer, is(notNullValue()));
		assertThat(customer.getId(), is(equalTo(1l)));
		assertThat(customer.getVendor(), is(notNullValue()));
		assertThat(customer.getVendor().getId(), is(equalTo(11l)));
		assertThat(customer.getSubscribedServices(), is(equalTo(null)));
	}

}
