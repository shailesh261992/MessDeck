package com.app.messdeck.model.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.test.data.CustomerDTODataSample;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class TestCustomerDTO extends AbstractUnitTest  {

	@Autowired
	private Validator validator;

	private CustomerDTO dto;

	@Before
	public void setUp() throws Exception {
		dto = CustomerDTODataSample.getCustomerDTO();
	}

	@Test
	public void testInvalidCustomerName() {
		dto.setName(new NameDTO("shaggy19", "Kadam"));
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("com.app.messdeck.model.dto.PersonDTO.name is invalid", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testInvalidCustomerAddress() {
		CustomerAddressDTO customerAddress = dto.getCustomerAddress();
		customerAddress.setCity("satara");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Invalid Customer Address", listOfVilolation.get(0).getMessage());
	}
	
	

}
