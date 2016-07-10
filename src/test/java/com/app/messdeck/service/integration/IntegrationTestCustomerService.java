package com.app.messdeck.service.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.businessException.CustomerNotExistsException;
import com.app.messdeck.businessException.MessDeckServiceInfoNotExistException;
import com.app.messdeck.service.CustomerService;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@DatabaseSetup(value = { "/dbunit/testdata/integrationData.xml" })
public class IntegrationTestCustomerService extends AbstractIntegrationTest {

	@Autowired
	private CustomerService service;

	@Test
	public void testListOfSubscribedServices() {

		assertThat(service.getListOfSubscribedServices(1l).size(), equalTo(2));
		assertThat(service.getListOfSubscribedServices(3l).size(), equalTo(0));

	}

	@Test
	public void testListOfSubscribedServices_NonExistingCustomer() {

		assertThat(service.getListOfSubscribedServices(1l).size(), equalTo(2));
		assertThat(service.getListOfSubscribedServices(3l).size(), equalTo(0));

	}

	@Test
	public void testSubScribeMessDeckService() {
		boolean subScribeMessDeckService = service.subScribeMessDeckService(2l, 3l);
		assertThat(subScribeMessDeckService, equalTo(true));
		assertThat(service.getListOfSubscribedServices(2l).size(), equalTo(2));

	}

	@Test(expected = CustomerNotExistsException.class)
	public void testSubScribeMessDeckService_NonExistingCustomer() {
		service.subScribeMessDeckService(Long.MAX_VALUE, 3l);

	}

	@Test(expected = MessDeckServiceInfoNotExistException.class)
	public void testSubScribeMessDeckService_NonExistingMessDeckService() {
		service.subScribeMessDeckService(2l, Long.MAX_VALUE);

	}

	@Test
	public void testUnSubScribeMessDeckService() {
		assertThat(service.unSubScribeMessDeckService(1l, 2l), equalTo(true));
		assertThat(service.getListOfSubscribedServices(1l).size(), equalTo(1));
	}

	@Test(expected = CustomerNotExistsException.class)
	public void testUnSubScribeMessDeckService_NonExistingCustomer() {
		service.unSubScribeMessDeckService(Long.MAX_VALUE, 2l);
	}

	@Test(expected = MessDeckServiceInfoNotExistException.class)
	public void testUnSubScribeMessDeckService_NonExistingMessDeckService() {
		service.unSubScribeMessDeckService(1l, Long.MAX_VALUE);
	}

}
