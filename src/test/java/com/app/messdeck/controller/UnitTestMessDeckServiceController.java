// package com.app.messdeck.controller;
//
// import static org.hamcrest.Matchers.hasItem;
// import static org.hamcrest.Matchers.is;
// import static org.mockito.Matchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.when;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import java.io.IOException;
// import java.util.ArrayList;
//
// import javax.transaction.Transactional;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
//
// import
// com.app.messdeck.businessException.MessDeckServiceInfoNotExistException;
// import com.app.messdeck.businessException.ValidationException;
// import com.app.messdeck.businessException.VendorNotExistException;
// import
// com.app.messdeck.configuration.testenvconfig.UnitTestConfigurationForControllers;
// import com.app.messdeck.entity.MessDeckServiceInfo;
// import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
// import com.app.messdeck.model.dto.ValidationErrrorInfo;
// import com.app.messdeck.modelmapper.DTOConverter;
// import com.app.messdeck.modelmapper.EntityConverter;
// import com.app.messdeck.service.MessDeckService;
// import com.app.messdeck.test.data.MessDeckServiceInfoDataSample;
// import com.app.messdeck.test.utils.TestUtils;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { UnitTestConfigurationForControllers.class
// })
// @WebAppConfiguration
// public class UnitTestMessDeckServiceController {
//
// private MediaType contentType = new
// MediaType(MediaType.APPLICATION_JSON.getType(),
// MediaType.APPLICATION_JSON.getSubtype());
// @Autowired
// MessDeckService serviceMock;
//
// private MockMvc mockMvc;
//
// @Autowired
// private WebApplicationContext webApplicationContext;
//
// private MessDeckServiceInfo messDeckServiceInfo;
// private MessDeckServiceInfoDTO messDeckServiceInfoDTO;
//
// @Before
// public void setUp() {
// Mockito.reset(serviceMock);
// mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
// messDeckServiceInfoDTO =
// MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
// messDeckServiceInfoDTO.setId(1);
// messDeckServiceInfo =
// DTOConverter.getMessDeckServiceInfo(messDeckServiceInfoDTO);
//
// }
//
// @Test
// public void testGetMessDeckService() throws Exception {
// when(serviceMock.getMessDeckService(1l))
// .thenReturn(EntityConverter.getMessDeckServiceInfoDTO(messDeckServiceInfo));
//
// mockMvc.perform(get("/messdeckservice/1").accept(contentType)).andDo(print()).andExpect(status().isOk())
// .andExpect(jsonPath("$.capacityOfMembers", is(50)))
// .andExpect(jsonPath("$.meal[*].name", hasItem("Rice")));
// }
//
// @Test
// public void testGetMessDeckServiceOrNonExisting() throws Exception {
// when(serviceMock.getMessDeckService(Long.MAX_VALUE))
// .thenThrow(new MessDeckServiceInfoNotExistException(Long.MAX_VALUE));
//
// mockMvc.perform(get("/messdeckservice/" +
// Long.MAX_VALUE).accept(contentType)).andDo(print())
// .andExpect(status().isBadRequest());
//
// }
//
// @Test
// public void testCreateMessDeckService() throws Exception {
// when(serviceMock.createMessDeckService(messDeckServiceInfoDTO)).thenReturn(1l);
// when(serviceMock.getMessDeckService(1l)).thenReturn(messDeckServiceInfoDTO);
//
// mockMvc.perform(post("/messdeckservice/").contentType(contentType).accept(contentType)
// .content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO))).andDo(print())
// .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
// }
//
// @Test
// public void testCreateMessDeckServiceWithNonExistingVendor() throws Exception
// {
// when(serviceMock.createMessDeckService(messDeckServiceInfoDTO))
// .thenThrow(new VendorNotExistException(Long.MAX_VALUE));
//
// mockMvc.perform(post("/messdeckservice/").contentType(contentType).accept(contentType)
// .content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO))).andDo(print())
// .andExpect(status().isBadRequest());
// }
//
// @Test
// public void testCreateWithInvalidData() throws Exception {
// ValidationErrrorInfo validationErrrorInfo = new ValidationErrrorInfo();
// validationErrrorInfo.setFieldName("cost");
// validationErrrorInfo.setErrorMessage("Cost must be greater than 0(Zero)");
// ArrayList<ValidationErrrorInfo> errors = new ArrayList<>();
// errors.add(validationErrrorInfo);
// when(serviceMock.createMessDeckService(messDeckServiceInfoDTO)).thenThrow(new
// ValidationException(errors));
// messDeckServiceInfoDTO.setCost(-2);
// mockMvc.perform(post("/messdeckservice/").contentType(contentType).accept(contentType)
// .content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO))).andDo(print())
// .andExpect(status().isBadRequest());
// }
//
// @Test
// public void testDelete() throws Exception {
// doNothing().when(serviceMock).deleteMessDeckService(1l);
//
// mockMvc.perform(delete("/messdeckservice/1")).andDo(print()).andExpect(status().isNoContent());
//
// }
//
// @Test
// public void testDeleteForNonExistingService() throws Exception {
// doThrow(new
// MessDeckServiceInfoNotExistException(Long.MAX_VALUE)).when(serviceMock)
// .deleteMessDeckService(Long.MAX_VALUE);
//
// mockMvc.perform(delete("/messdeckservice/" +
// Long.MAX_VALUE)).andDo(print()).andExpect(status().isBadRequest());
//
// }
//
// @Test
// public void testUpdate() throws Exception {
// messDeckServiceInfoDTO.setId(1);
// when(serviceMock.getMessDeckService(1l)).thenReturn(messDeckServiceInfoDTO);
//
// MvcResult result =
// mockMvc.perform(get("/messdeckservice/1").accept(contentType)).andReturn();
// MockHttpServletResponse response = result.getResponse();
// String content = response.getContentAsString();
//
// MessDeckServiceInfoDTO updatedMessDeckService = (MessDeckServiceInfoDTO)
// TestUtils.convertJsonToObject(content,
// MessDeckServiceInfoDTO.class);
// updatedMessDeckService.setCost(213);
//
// doNothing().when(serviceMock).updateMessDeckService(updatedMessDeckService);
// when(serviceMock.getMessDeckService(1l)).thenReturn(updatedMessDeckService);
//
// mockMvc.perform(put("/messdeckservice/").contentType(contentType)
// .content(TestUtils.convertObjectToJsonString(updatedMessDeckService)).accept(contentType))
// .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.cost",
// is(new Double(213))));
//
// }
//
// @Test
// public void testUpdateNonExistingMessDeckService() throws Exception {
// doThrow(MessDeckServiceInfoNotExistException.class).when(serviceMock)
// .updateMessDeckService(messDeckServiceInfoDTO);
//
// mockMvc.perform(put("/messdeckservice/").contentType(contentType)
// .content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO)).accept(contentType))
// .andDo(print()).andExpect(status().isBadRequest());
//
// }
//
// @Test
// public void testUpdateWithVendorOtherThanVendorWhichCreatesService() throws
// Exception {
// doThrow(MessDeckServiceInfoNotExistException.class).when(serviceMock)
// .updateMessDeckService(messDeckServiceInfoDTO);
//
// mockMvc.perform(put("/messdeckservice/").contentType(contentType)
// .content(TestUtils.convertObjectToJsonString(messDeckServiceInfoDTO)).accept(contentType))
// .andDo(print()).andExpect(status().isBadRequest());
//
// }
//
// }
