package com.app.messdeck.controller.integration;

import static org.hamcrest.Matchers.containsInAnyOrder;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.test.data.CustomerDTODataSample;
import com.app.messdeck.utils.TestUtils;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@DatabaseSetup(value = { "/dbunit/testdata/integrationData.xml" })
public class IntegrationTestCustomerController extends AbstractIntegrationTest {

	@Test
	public void testGetCustomerSummary() throws Exception {

		mockMvc.perform(get("/customers/1").accept(contentType)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name.firstName", is("Ganesh")));

	}

	@Test
	public void testGetCustomerSummaryForNonExistingCustomer() throws Exception {

		mockMvc.perform(get("/customers/17171717").accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateCustomer() throws Exception {

		CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
		customerDTO.setVendorId(1l);
		mockMvc.perform(post("/customers").contentType(contentType).accept(contentType)
				.content(TestUtils.convertObjectToJsonString(customerDTO))).andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	public void testCreateCustomerWithInvalidData() throws IOException, Exception {

		CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
		customerDTO.setVendorId(1l);
		customerDTO.setMobileNo("123");

		mockMvc.perform(post("/customers").contentType(contentType).accept(contentType)
				.content(TestUtils.convertObjectToJsonString(customerDTO))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[*].fieldName", containsInAnyOrder("mobileNo"))).andDo(print());

	}

	@Test
	public void testCreateCustomerWithNonExistingVendor() throws IOException, Exception {

		CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
		customerDTO.setVendorId(Long.MAX_VALUE);
		mockMvc.perform(post("/customers").contentType(contentType).accept(contentType)
				.content(TestUtils.convertObjectToJsonString(customerDTO))).andDo(print());

	}

	@Test
	public void testDeleteCustomer() throws Exception {
		mockMvc.perform(delete("/customers/2")).andDo(print()).andExpect(status().isNoContent());

	}

	@Test
	public void testDeleteNonExistingCustomer() throws Exception {
		mockMvc.perform(delete("/customers/" + Integer.MAX_VALUE)).andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testUpdateCustomer() throws IOException, Exception {

		MvcResult result = mockMvc.perform(get("/customers/3").accept(contentType)).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String content = response.getContentAsString();

		CustomerDTO customerDTO = (CustomerDTO) TestUtils.convertJsonToObject(content, CustomerDTO.class);
		customerDTO.setMobileNo("9898676776");
		mockMvc.perform(
				put("/customers/3").contentType(contentType).content(TestUtils.convertObjectToJsonString(customerDTO)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.mobileNo", is("9898676776")));

	}

	@Test
	public void testUpdateNonExistingCustomer() throws IOException, Exception {
		CustomerDTO customerDTO = CustomerDTODataSample.getCustomerDTO();
		customerDTO.setMobileNo("9876598765");
		customerDTO.setVendorId(1l);

		mockMvc.perform(put("/customers/" + Integer.MAX_VALUE).contentType(contentType)
				.content(TestUtils.convertObjectToJsonString(customerDTO))).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testUpdateNonExistingVendorReferredByCustomer() throws IOException, Exception {
		MvcResult result = mockMvc.perform(get("/customers/3").accept(contentType)).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String content = response.getContentAsString();

		CustomerDTO customerDTO = (CustomerDTO) TestUtils.convertJsonToObject(content, CustomerDTO.class);
		customerDTO.setMobileNo("9898676776");
		customerDTO.setVendorId(Long.MAX_VALUE);
		mockMvc.perform(
				put("/customers/3").contentType(contentType).content(TestUtils.convertObjectToJsonString(customerDTO)))
				.andDo(print());

	}

	@Test
	public void testSubscribeService() throws IOException, Exception {
		mockMvc.perform(post("/customers/1/subscribe/3").accept(contentType)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testSubscribeService_NonExistingCustomer() throws IOException, Exception {
		mockMvc.perform(post("/customers/" + Long.MAX_VALUE + "/subscribe/3").accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testSubscribeService_NonExistingMessDeckService() throws IOException, Exception {
		mockMvc.perform(post("/customers/1/subscribe/" + Long.MAX_VALUE).accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testUnSubscribeService() throws IOException, Exception {
		mockMvc.perform(post("/customers/1/unsubscribe/2").accept(contentType)).andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void testUnSubscribeService_NonExistingCustomer() throws IOException, Exception {
		mockMvc.perform(post("/customers/" + Long.MAX_VALUE + "/unsubscribe/3").accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testUnSubscribeService_NonExistingMessDeckService() throws IOException, Exception {
		mockMvc.perform(post("/customers/1/unsubscribe/" + Long.MAX_VALUE).accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testGetListOfSubscribedServices() throws Exception {
		mockMvc.perform(get("/customers/1/subscribedServices/").accept(contentType)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetListOfSubscribedServices_NonExistingCustomer() throws Exception {
		mockMvc.perform(get("/customers/" + Long.MAX_VALUE + "/subscribedServices/").accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());
	}

}
