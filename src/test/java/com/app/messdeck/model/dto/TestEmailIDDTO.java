package com.app.messdeck.model.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.test.data.EmailIDDTODataSample;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class TestEmailIDDTO extends AbstractUnitTest {

	@Autowired
	private Validator validator;

	private EmailIDDTO dto;

	@Before
	public void setUp() throws Exception {
		dto = EmailIDDTODataSample.getEmailIDDTO_OwnerEmailID();
	}

	@Test
	public void testInvalidEmailID() {
		dto.setEmailId("shailesh@gmail");
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("Invalid EmailID", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testNullEmailID() {
		dto.setEmailId(null);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(1, listOfVilolation.size());
		assertEquals("EmailID  can not be null", listOfVilolation.get(0).getMessage());
	}

	@Test
	public void testValidEmailID() {
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(0, listOfVilolation.size());

	}

}
