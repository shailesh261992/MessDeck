//package com.app.messdeck.service;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import com.app.messdeck.businessException.VendorNotExistException;
//import com.app.messdeck.entity.Customer;
//import com.app.messdeck.entity.Vendor;
//import com.app.messdeck.model.dto.CustomerDTO;
//import com.app.messdeck.model.dto.VendorDTO;
//import com.app.messdeck.repository.VendorDAO;
//import com.app.messdeck.test.data.CustomerDTODataSample;
//import com.app.messdeck.test.data.VendorDTODataSample;
//import com.app.messdeck.utility.DTOConverter;
//
//public class TestVendorServiceImpl {
//
//	@Mock
//	VendorDAO dao;
//
//	@InjectMocks
//	VendorServiceImpl service = new VendorServiceImpl();
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	@Ignore
//	public void testVendorCreate() {
//		VendorDTO vendorDTO = VendorDTODataSample.getVendorDTO();
//		when(dao.create(any(Vendor.class))).thenReturn(1l);
//		Long vendorID = service.createVendor(vendorDTO);
//		assertEquals(1l, (long) vendorID);
//		ArgumentCaptor<Vendor> captor = ArgumentCaptor.forClass(Vendor.class);
//		verify(dao).create(captor.capture());
//		assertEquals(DTOConverter.getVendor(vendorDTO), captor.getValue());
//
//	}
//
//	@Test(expected = DataIntegrityViolationException.class)
//	@Ignore
//	public void testVendorCreateWithIncorrectData() {
//
//		when(dao.create(any(Vendor.class))).thenThrow(new DataIntegrityViolationException(""));
//		service.createVendor(VendorDTODataSample.getVendorDTO());
//
//	}
//
//	@Test
//	@Ignore
//	public void testGetVendorSummary() {
//		Vendor vendor = DTOConverter.getVendor(VendorDTODataSample.getVendorDTO());
//		vendor.setId(1);
//		when(dao.get(1)).thenReturn(vendor);
//		VendorDTO dto = service.getVendorSummary(1l);
//		assertEquals(1, dto.getId());
//		assertNull(dto.getOwner());
//		assertNotNull(dto.getVendorAddress());
//
//	}
//
//	@Test
//	@Ignore
//	public void testGetVendorDetails() {
//		Vendor vendor = DTOConverter.getVendor(VendorDTODataSample.getVendorDTO());
//		vendor.setId(1);
//		when(dao.get(1)).thenReturn(vendor);
//		VendorDTO dto = service.getVendorDetails(1l);
//		assertEquals(1, dto.getId());
//		assertNotNull(dto.getOwner());
//		assertNotNull(dto.getVendorAddress());
//
//	}
//
//	@Test
//	@Ignore
//	public void testVendorDelete() {
//		Vendor vendor = DTOConverter.getVendor(VendorDTODataSample.getVendorDTO());
//		vendor.setId(1);
//		when(dao.get(1)).thenReturn(vendor);
//		doNothing().when(dao).delete(vendor);
//		service.deleteVendor(1l);
//
//		verify(dao).delete(vendor);
//		verify(dao).get(1l);
//
//	}
//
//	@Test
//	@Ignore
//	public void testGetCustomers() {
//		Vendor vendor = DTOConverter.getVendor(VendorDTODataSample.getVendorDTO());
//		vendor.setId(1);
//		List<Customer> customers = new ArrayList<>();
//		customers.add(DTOConverter.getCustomer(CustomerDTODataSample.getCustomerDTO()));
//		vendor.setCustomers(customers);
//		when(dao.get(1)).thenReturn(vendor);
//
//		List<CustomerDTO> list = service.getCustomrs(1l);
//		assertEquals(1, list.size());
//	}
//
//	@Test(expected = VendorNotExistException.class)
//	@Ignore
//	public void testGetCustomersForNonExistingVendor() {
//
//		when(dao.get(1)).thenThrow(new VendorNotExistException(1));
//		service.getCustomrs(1l);
//
//	}
//
//}
