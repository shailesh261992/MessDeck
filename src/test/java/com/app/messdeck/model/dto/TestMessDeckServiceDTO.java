package com.app.messdeck.model.dto;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.messdeck.abstracttest.AbstractUnitTest;
import com.app.messdeck.test.data.MessDeckServiceInfoDataSample;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class TestMessDeckServiceDTO extends AbstractUnitTest {

	@Autowired
	private Validator validator;

	private MessDeckServiceInfoDTO dto;

	@Before
	public void setUp() throws Exception {
		dto = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
	}

	@Test
	public void testNullVendor() {
		dto.setVendor(0);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals("VendorId can not be less than equal to zero,Only vendors are allowed to publish service",
				listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testInalidCapacityOfMembers() {
		dto.setCapacityOfMembers(-1);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals("Member Capacity must be greater than 0(Zero)", listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testInalidCost() {
		dto.setCost(-1);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals("Cost must be greater than 0(Zero)", listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testStartTimeGreaterThanEndTime() {
		LocalTime startTime = LocalTime.now();
		LocalTime endTime = startTime.minus(2, ChronoUnit.HOURS);
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals("Service start time must be less than end time", listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testServiceWithPastDate() {

		dto.setDate(LocalDate.now().minus(2, ChronoUnit.DAYS));

		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals("Date must be greate than eq to current date & must be less than laste date in next month",
				listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testServiceWithDateAboveThreshold() {

		dto.setDate(LocalDate.now().plus(65, ChronoUnit.DAYS));

		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals("Date must be greate than eq to current date & must be less than laste date in next month",
				listOfVilolation.get(0).getMessage());

	}

	@Test
	public void testServiceWithDateBetweenCurrentAndThreshold() {

		dto.setDate(LocalDate.now().plus(10, ChronoUnit.DAYS));

		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(0, listOfVilolation.size());

	}

	@Test
	public void testValidMessDeckService() {
		List<ConstraintViolation> listOfVilolation = validator.validate(dto);
		assertEquals(0, listOfVilolation.size());

	}

}
