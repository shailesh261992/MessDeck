package com.app.messdeck.controller.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;

import com.app.messdeck.abstracttest.AbstractIntegrationTest;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.test.data.MessDeckServiceInfoDataSample;
import com.app.messdeck.utils.TestUtils;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@DatabaseSetup(value = { "/dbunit/testdata/MessDeckServiceInfo.xml" })
public class MessDeckServiceController extends AbstractIntegrationTest {

	@Test
	@Ignore
	public void testGetMessDeckService() throws Exception {

		mockMvc.perform(get("/messdeckservice/1").accept(contentType)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.capacityOfMembers", is(46)));
	}

	@Test
	@Ignore
	public void testNonExistingGetMessDeckService() throws Exception {

		mockMvc.perform(get("/messdeckservice/" + Long.MAX_VALUE).accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	@Ignore
	public void testCreateMessDeckService() throws Exception {
		MessDeckServiceInfoDTO messDeckServiceInfoDTO = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
		messDeckServiceInfoDTO.setVendor(1);

		mockMvc.perform(post("/messdeckservice/").contentType(contentType).accept(contentType)
				.content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO))).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.capacityOfMembers", is(50)))
				.andExpect(jsonPath("$.meal[*].name", is(hasItem(equalTo("Rice")))));

	}

	@Test
	@Ignore
	public void testCreateMessDeckServiceByNonExistingVendor() throws Exception {
		MessDeckServiceInfoDTO messDeckServiceInfoDTO = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
		messDeckServiceInfoDTO.setVendor(Long.MAX_VALUE);
		mockMvc.perform(post("/messdeckservice/").contentType(contentType).accept(contentType)
				.content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO))).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	@Ignore
	public void testDeleteMessDeckService() throws Exception {

		mockMvc.perform(delete("/messdeckservice/1").accept(contentType)).andDo(print())
				.andExpect(status().isNoContent());

	}

	@Test
	@Ignore
	public void testDeleteNonExistingMessDeckService() throws Exception {

		mockMvc.perform(delete("/messdeckservice/" + Long.MAX_VALUE).accept(contentType)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	@Ignore
	public void testUpdateMessDeckService() throws Exception {

		MessDeckServiceInfoDTO messDeckServiceInfoDTO = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
		messDeckServiceInfoDTO.setId(1);
		messDeckServiceInfoDTO.setCost(99.9);

		mockMvc.perform(put("/messdeckservice/").contentType(contentType)
				.content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO)).accept(contentType))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.cost", is(equalTo(99.9))));

	}

	@Test
	@Ignore
	public void testUpdateNonExistingMessDeckService() throws Exception {

		MessDeckServiceInfoDTO messDeckServiceInfoDTO = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
		messDeckServiceInfoDTO.setId(Long.MAX_VALUE);
		messDeckServiceInfoDTO.setCost(99.9);

		mockMvc.perform(put("/messdeckservice/").contentType(contentType)
				.content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO)).accept(contentType))
				.andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	@Ignore
	public void testForNotToAllowVendorUpdateForService() throws Exception {

		MessDeckServiceInfoDTO messDeckServiceInfoDTO = MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
		messDeckServiceInfoDTO.setId(1);
		messDeckServiceInfoDTO.setVendor(2);
		messDeckServiceInfoDTO.setCost(99.9);

		mockMvc.perform(put("/messdeckservice/").contentType(contentType)
				.content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO)).accept(contentType))
				.andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testGetSubscribersForMessDeckService() throws Exception {

		mockMvc.perform(get("/messdeckservice/1/subscribers").accept(contentType)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[*].name.firstName", is(hasItem(equalTo("Ganesh")))));

	}
}
