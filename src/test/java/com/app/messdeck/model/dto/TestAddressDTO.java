package com.app.messdeck.model.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.test.data.AddressDTODataSample;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class TestAddressDTO extends AbstractUnitTest {

	@Autowired
	private Validator validator;

	private AddressDTO dto;

	@Before
	public void setUp() throws Exception {
		dto = AddressDTODataSample.getAddressDTO();
	}

	@Test
	public void testInvalidPincode() {
		dto.setPinCode("10507");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Invalid Pincode", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testInvalidCountry() {
		dto.setCountry("In");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Only Supported in India. Valid values list [India]", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testInvalidState() {
		dto.setState("maharashtraa");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Only Supported in Maharashtra. Valid values list [Maharashtra]",
				listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testInvalidCity() {
		dto.setCity("satara");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Only Supported in Pune. Valid values list [Pune]", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testValidAddressDTO() {
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(0, listOfVilolation.size());
	}

}
