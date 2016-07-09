package com.app.messdeck.service.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.service.VendorService;

public class VendorServiceIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private VendorService service;

	@Test
	public void test() {
		// VendorDTO vendorSummary = service.getVendorSummary(1l);
		// assertThat(1l, is(equalTo(vendorSummary.getId())));
		// System.out.println(vendorSummary);
	}

}
