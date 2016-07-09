package com.app.messdeck.controller.integration;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Test;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.model.dto.EmailIDDTO;
import com.app.messdeck.model.dto.VendorDTO;
import com.app.messdeck.test.data.VendorDTODataSample;
import com.app.messdeck.utils.TestUtils;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@DatabaseSetup("/dbunit/testdata/VendorData.xml")
public class IntegrationTestVendorController extends AbstractIntegrationTest {

	@Test

	public void testGetVendorSummary() throws Exception {
		mockMvc.perform(get("/vendors/1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Sai Mess")))
				.andExpect(jsonPath("$.vendorAddress.pinCode", is("410507")));
	}

	@Test
	public void testGetVendorSummaryForNonExistingVendor() throws Exception {
		mockMvc.perform(get("/vendors/15")).andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testGetVendorDetails() throws Exception {
		mockMvc.perform(get("/vendors/1/details")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Sai Mess")))
				.andExpect(jsonPath("$.owner.mobileNo", is("7276248187")));

	}

	@Test
	public void testGetVendorDetailsForNonExistingVendor() throws Exception {
		mockMvc.perform(get("/vendors/123456234/details")).andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testCreateVendor() throws Exception {
		VendorDTO vendor = VendorDTODataSample.getVendorDTO();
		vendor.setName("Arya mess");
		vendor.getOwner().setEmailID(new EmailIDDTO("arya@gmail.com"));
		mockMvc.perform(post("/vendors").contentType(contentType).content(TestUtils.convertObjectToJsonString(vendor)))
				.andDo(print()).andExpect(status().isCreated());

	}

	@Test
	public void testCreateVendorWithConstraintViolation() throws Exception {
		VendorDTO vendor = VendorDTODataSample.getVendorDTO();
		vendor.setName("Sai9 Dhaba");
		vendor.getOwner().setEmailID(new EmailIDDTO("sai9@gmail.com"));

		mockMvc.perform(post("/vendors").contentType(contentType).content(TestUtils.convertObjectToJsonString(vendor)))
				.andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$[0].fieldName", is("name")));

	}

	@Test
	public void testCreateVendorWithConstraintViolation2() throws Exception {
		VendorDTO vendor = VendorDTODataSample.getVendorDTO();

		vendor.getVendorAddress().setCountry("US");
		vendor.getOwner().setEmailID(new EmailIDDTO("sai9@gmail.com"));

		mockMvc.perform(post("/vendors").contentType(contentType).content(TestUtils.convertObjectToJsonString(vendor)))
				.andExpect(status().isBadRequest()).andDo(print())
				.andExpect(jsonPath("$[0].fieldName", is("vendorAddress")));
	}

	@Test
	public void testVendorDelete() throws Exception {
		mockMvc.perform(delete("/vendors/3")).andDo(print()).andExpect(status().isNoContent());

	}

	@Test
	public void testVendorDeleteWithNonExistingVendor() throws Exception {
		mockMvc.perform(delete("/vendors/12345675")).andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testVendorUpdate() throws IOException, Exception {

		VendorDTO dto = VendorDTODataSample.getVendorDTO();
		dto.setId(1);
		dto.getOwner().setId(25); // trying to give wrong id of owner . It
									// should ignore this id

		mockMvc.perform(put("/vendors/2").contentType(contentType).content(TestUtils.convertObjectToJsonString(dto)))
				.andDo(print()).andExpect(status().isNoContent());

	}

	@Test
	public void testVendorUpdateWithInvalidData() throws IOException, Exception {

		VendorDTO dto = VendorDTODataSample.getVendorDTO();
		dto.setName("Andha 9");// trying to give wrong id of owner . It
								// should ignore this id

		mockMvc.perform(put("/vendors/2").contentType(contentType).content(TestUtils.convertObjectToJsonString(dto)))
				.andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$[0].fieldName", is("name")));

	}

	@Test
	public void testVendorUpdateWithNonExistingVendor() throws IOException, Exception {
		VendorDTO dto = VendorDTODataSample.getVendorDTO();

		mockMvc.perform(
				put("/vendors/123456456").contentType(contentType).content(TestUtils.convertObjectToJsonString(dto)))
				.andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void getAllVendorsSummary() throws Exception {
		mockMvc.perform(get("/vendors/all")).andDo(print()).andExpect(status().isOk());

	}

	// @Test
	// public void testGetCustomers() throws Exception {
	// mockMvc.perform(get("/vendors/1/customers").accept(contentType)).andDo(print()).andExpect(status().isOk())
	// .andExpect(jsonPath("$[0].mobileNo", is("7770092161")));
	//
	// }

	@Test
	public void testGetCustomersForNonExistingVendor() throws Exception {

		mockMvc.perform(get("/vendors/" + Integer.MAX_VALUE + "/customers").accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

}
