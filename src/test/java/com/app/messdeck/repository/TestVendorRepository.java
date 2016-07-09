package com.app.messdeck.repository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.repository.VendorRepository;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class TestVendorRepository extends AbstractIntegrationTest {
	@Autowired
	private VendorRepository repo;


	@Test
	@DatabaseSetup(value = { "/dbunit/OwnersAddressData.xml", "/dbunit/OwnersData.xml", "/dbunit/VendorsAddress.xml",
			"/dbunit/VendorsData.xml" })

	public void test() {
		System.out.println("Total records = " + repo.count());
		repo.findAll().stream().forEach(x -> System.out.println("name = " + x.getName()));
	}

}
