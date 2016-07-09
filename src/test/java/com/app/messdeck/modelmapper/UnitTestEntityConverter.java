package com.app.messdeck.modelmapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.entity.Customer;
import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.entity.Vendor;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.test.data.CustomerDTODataSample;
import com.app.messdeck.test.data.MessDeckServiceInfoDataSample;

public class UnitTestEntityConverter extends AbstractUnitTest {

	@Test
	public void testMessDeckServiceInfo_to_DTOConversion() {

		MessDeckServiceInfo messDeckServiceInfo = DTOConverter
				.getMessDeckServiceInfo(MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO());
		messDeckServiceInfo.getMeal().get(0).setId(10);

		MessDeckServiceInfoDTO messDeckServiceInfoDTO = EntityConverter.getMessDeckServiceInfoDTO(messDeckServiceInfo);

		assertThat(messDeckServiceInfoDTO, is(notNullValue()));
		assertThat(messDeckServiceInfoDTO.getVendorId(), is(equalTo(1l)));

		assertThat(messDeckServiceInfo.getMeal(), is(notNullValue()));
		assertThat(messDeckServiceInfo.getMeal().get(0).getId(), equalTo(10l));

	}

	@Test
	public void testCustomer_to_CustomerDTO() {

		Customer customer = DTOConverter.getCustomer(CustomerDTODataSample.getCustomerDTO());
		Vendor vendor = new Vendor();
		vendor.setId(17);
		customer.setVendor(vendor);
		customer.setId(5);

		CustomerDTO customerDTO = EntityConverter.getCustomorSummaryDTO(customer);
		assertThat(customerDTO, is(notNullValue()));
		assertThat(customerDTO.getId(), is(equalTo(5l)));
		assertThat(customerDTO.getVendorId(), is(equalTo(17l)));

	}

}
