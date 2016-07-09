package com.app.messdeck.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.repository.CustomerRepository;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class TestCustomerRepository extends AbstractIntegrationTest {

	@Autowired
	private CustomerRepository repository;

	@Test
	public void test() {
		System.out.println("Total records = " + repository.count());
		repository.findAll().stream().forEach(x -> System.out.println("name = " + x.getName()));
	}

}
