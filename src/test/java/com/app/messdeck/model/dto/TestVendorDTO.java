package com.app.messdeck.model.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.test.data.VendorDTODataSample;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class TestVendorDTO extends AbstractUnitTest {

	@Autowired
	private Validator validator;

	private VendorDTO dto;

	@Before
	public void setUp() throws Exception {
		dto = VendorDTODataSample.getVendorDTO();
	}

	@Test
	public void testInvalidVendorName() {
		dto.setName("Sai 123");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Only Alphabets are allowed", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testInvalidVendorEmailID() {
		dto.setEmailID(new EmailIDDTO("shaggy@"));
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("com.app.messdeck.model.dto.VendorDTO.emailID is invalid", listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testInvalidVendorAddress() {
		VendorAddressDTO vendorAddressDTO = new VendorAddressDTO();
		vendorAddressDTO.setCity("Satara");
		dto.setVendorAddress(vendorAddressDTO);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Invalid Vendor Address", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testInvalidOwner() {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setMobileNo("123");
		dto.setOwner(ownerDTO);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Invalid Owner", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testValidVendor() {

		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(0, listOfVilolation.size());

	}

}
