// package com.app.messdeck.controller;
//
// import static org.hamcrest.Matchers.is;
// import static org.junit.Assert.assertEquals;
// import static org.mockito.Matchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
//
// import com.app.messdeck.businessException.ValidationException;
// import com.app.messdeck.businessException.VendorNotExistException;
// import com.app.messdeck.model.dto.ValidationErrrorInfo;
// import com.app.messdeck.model.dto.VendorDTO;
// import com.app.messdeck.service.VendorService;
// import com.app.messdeck.test.data.VendorDTODataSample;
// import com.app.messdeck.test.utils.TestUtils;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { UnitTestConfigurationForControllers.class
// })
// @WebAppConfiguration
//
// public class UnitTestVendorController {
//
// private MediaType contentType = new
// MediaType(MediaType.APPLICATION_JSON.getType(),
// MediaType.APPLICATION_JSON.getSubtype());
// @Autowired
// VendorService serviceMock;
//
// private MockMvc mockMvc;
//
// @Autowired
// private WebApplicationContext webApplicationContext;
//
// @Before
// public void setUp() {
// Mockito.reset(serviceMock);
// mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//
// }
//
// @Test
// public void testGetVendorSummary() throws Exception {
// VendorDTO vendor = VendorDTODataSample.getVendorDTO();
// vendor.setOwner(null);
// vendor.setCustomers(null);
// vendor.setServices(null);
//
// when(serviceMock.getVendorSummary(1l)).thenReturn(vendor);
//
// mockMvc.perform(get("/vendors/1")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
// .andExpect(jsonPath("$.name", is("Andhra
// Mess"))).andExpect(jsonPath("$.links[0].rel", is("self")))
// .andDo(print());
//
// }
//
// @Test
// public void testGetVendorSummaryForNonExistingVendor() throws Exception {
//
// when(serviceMock.getVendorSummary(1l)).thenThrow(new
// VendorNotExistException(1));
// mockMvc.perform(get("/vendors/1")).andDo(print()).andExpect(status().isBadRequest());
//
// }
//
// @Test
// public void testGetVendorDetails() throws Exception {
// VendorDTO vendor = VendorDTODataSample.getVendorDTO();
// vendor.setCustomers(null);
// vendor.setServices(null);
// when(serviceMock.getVendorDetails(1l)).thenReturn(vendor);
//
// mockMvc.perform(get("/vendors/1/details")).andDo(print()).andExpect(status().isOk())
// .andExpect(jsonPath("$.name", is("Andhra Mess")))
// .andExpect(jsonPath("$.owner.mobileNo", is("9876543210")));
//
// }
//
// @Test
// public void testGetVendorDetailsForNonExistingVendor() throws Exception {
// VendorDTO vendor = VendorDTODataSample.getVendorDTO();
// vendor.setCustomers(null);
// vendor.setServices(null);
// when(serviceMock.getVendorDetails(1l)).thenThrow(new
// VendorNotExistException(1));
//
// mockMvc.perform(get("/vendors/1/details")).andDo(print()).andExpect(status().isBadRequest());
//
//
// }
//
// @Test
// public void testCreateVendor() throws Exception {
// VendorDTO vendor = VendorDTODataSample.getVendorDTO();
//
// when(serviceMock.createVendor(vendor)).thenReturn(1l);
//
// mockMvc.perform(post("/vendors").contentType(contentType).content(TestUtils.convertObjectToJsonString(vendor)))
// .andExpect(status().isCreated()).andDo(print());
//
// }
//
// @Test
// public void testCreateVendorWithConstraintViolation() throws Exception {
// VendorDTO vendor = VendorDTODataSample.getVendorDTO();
// vendor.setId(1);
// vendor.setName("Sai9 Dhaba");
//
// List<ValidationErrrorInfo> errorInfoList = new ArrayList<>();
// ValidationErrrorInfo validationErrrorInfo = new ValidationErrrorInfo();
// validationErrrorInfo.setFieldName("name");
// validationErrrorInfo.setErrorMessage("Inavlide Vendor Name");
// errorInfoList.add(validationErrrorInfo);
//
// ValidationException exception = new ValidationException(errorInfoList);
// when(serviceMock.createVendor(vendor)).thenThrow(exception);
//
// mockMvc.perform(post("/vendors").contentType(contentType).content(TestUtils.convertObjectToJsonString(vendor)))
// .andExpect(status().isBadRequest()).andDo(print());
//
// }
//
// @Test
// public void testVendorDelete() throws Exception {
// doNothing().when(serviceMock).deleteVendor(1l);
// ;
//
// mockMvc.perform(delete("/vendors/1")).andExpect(status().isNoContent()).andDo(print());
//
// }
//
// @Test
// public void testVendorDeleteWithNonExistingVendor() throws Exception {
// doThrow(VendorNotExistException.class).when(serviceMock).deleteVendor(1l);
// ;
//
// mockMvc.perform(delete("/vendors/1")).andExpect(status().isBadRequest()).andDo(print());
//
// }
//
// @Test
// public void testVendorUpdate() throws IOException, Exception {
//
// VendorDTO dto = VendorDTODataSample.getVendorDTO();
// dto.setId(1);
// dto.getOwner().setId(25); // trying to give wrong id of owner . It
// // should ignore this id
// doNothing().when(serviceMock).updateVendor(any(VendorDTO.class));
// mockMvc.perform(put("/vendors/1").contentType(contentType).content(TestUtils.convertObjectToJsonString(dto)))
// .andDo(print());
//
// ArgumentCaptor<VendorDTO> captor = ArgumentCaptor.forClass(VendorDTO.class);
// verify(serviceMock).updateVendor(captor.capture());
// VendorDTO value = captor.getValue();
// assertEquals(1, value.getId());
// assertEquals(0, value.getOwner().getId());
// System.out.println("Captured value = " + value);
//
// }
//
// @Test
// public void testVendorUpdateWithNonExistingVendor() throws IOException,
// Exception {
// VendorDTO dto = VendorDTODataSample.getVendorDTO();
// dto.setId(1);
// doThrow(VendorNotExistException.class).when(serviceMock).updateVendor(any(VendorDTO.class));
//
// mockMvc.perform(
// put("/vendors/123456456").contentType(contentType).content(TestUtils.convertObjectToJsonString(dto)))
// .andDo(print()).andExpect(status().isBadRequest());
//
// ArgumentCaptor<VendorDTO> captor = ArgumentCaptor.forClass(VendorDTO.class);
//
// verify(serviceMock).updateVendor(captor.capture());
// VendorDTO value = captor.getValue();
// assertEquals(123456456, value.getId());
//
// }
//
// }
