// package com.app.messdeck.service;
//
// import static org.junit.Assert.assertEquals;
// import static org.mockito.Matchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
//
// import javax.transaction.Transactional;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.web.WebAppConfiguration;
//
// import
// com.app.messdeck.businessException.MessDeckServiceInfoNotExistException;
// import
// com.app.messdeck.businessException.NotVendorWhoCreatesServiceException;
// import com.app.messdeck.businessException.ValidationException;
// import com.app.messdeck.businessException.VendorNotExistException;
// import
// com.app.messdeck.configuration.testenvconfig.UnitTestConfigurationForServices;
// import com.app.messdeck.entity.MessDeckServiceInfo;
// import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
// import com.app.messdeck.modelmapper.DTOConverter;
// import com.app.messdeck.repository.MessDeckServiceInfoDAO;
// import com.app.messdeck.repository.VendorDAO;
// import com.app.messdeck.test.data.MessDeckServiceInfoDataSample;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { UnitTestConfigurationForServices.class })
// @WebAppConfiguration
// public class TestMessDeckService {
//
// @Autowired
// private MessDeckService service;
//
// @Autowired
// MessDeckServiceInfoDAO daoMock;
//
// @Autowired
// VendorDAO vendorDaoMock;
//
// private MessDeckServiceInfoDTO messDeckServiceInfoDTO;
// private MessDeckServiceInfo messDeckServiceInfo;
//
// @Before
// public void setUp() throws Exception {
// Mockito.reset(daoMock);
// messDeckServiceInfoDTO =
// MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
// messDeckServiceInfo =
// DTOConverter.getMessDeckServiceInfo(messDeckServiceInfoDTO);
// }
//
// @Test
// public void testCreate() {
//
// when(daoMock.create(messDeckServiceInfo)).thenReturn(1l);
// Long id = service.createMessDeckService(messDeckServiceInfoDTO);
// assertEquals(new Long(1), id);
//
// }
//
// @Test(expected = ValidationException.class)
// public void testCreateWithInvalidData() {
//
// messDeckServiceInfoDTO.setCost(-2);
// when(daoMock.create(messDeckServiceInfo)).thenReturn(1l);
// service.createMessDeckService(messDeckServiceInfoDTO);
//
// }
//
// @Test(expected = VendorNotExistException.class)
// public void tesCreateWithNonExistingVendor() {
//
// messDeckServiceInfoDTO.setId(1);
//
// when(daoMock.create(DTOConverter.getMessDeckServiceInfo(messDeckServiceInfoDTO)))
// .thenThrow(new VendorNotExistException(1));
// service.createMessDeckService(messDeckServiceInfoDTO);
//
// }
//
// @Test
// public void testGetMessDeckService() {
//
// messDeckServiceInfoDTO.setId(1);
//
// when(daoMock.get(1l)).thenReturn(messDeckServiceInfo);
//
// messDeckServiceInfoDTO.equals(service.getMessDeckService(1l));
// assertEquals(messDeckServiceInfoDTO, service.getMessDeckService(1l));
// }
//
// @Test(expected = MessDeckServiceInfoNotExistException.class)
// public void testGetForNonExistingMessDeckService() {
// when(daoMock.get(Long.MAX_VALUE)).thenThrow(new
// MessDeckServiceInfoNotExistException(Long.MAX_VALUE));
// service.getMessDeckService(Long.MAX_VALUE);
// }
//
// @Test
// public void testDelete() {
// doNothing().when(daoMock).delete(1l);
// service.deleteMessDeckService(1l);
// verify(daoMock).delete(1l);
//
// }
//
// @Test(expected = MessDeckServiceInfoNotExistException.class)
// public void testDeleteForNonExistingService() {
// doThrow(MessDeckServiceInfoNotExistException.class).when(daoMock).delete(Long.MAX_VALUE);
// service.deleteMessDeckService(Long.MAX_VALUE);
// }
//
// @Test
// public void testUpdate() {
//
// MessDeckServiceInfoDTO updatedMessDeckServiceDTO =
// MessDeckServiceInfoDataSample.getMessDeckServiceInfoDTO();
// updatedMessDeckServiceDTO.setCost(244);
// when(daoMock.get(1l)).thenReturn(DTOConverter.getMessDeckServiceInfo(messDeckServiceInfoDTO))
// .thenReturn(DTOConverter.getMessDeckServiceInfo(updatedMessDeckServiceDTO));
// service.getMessDeckService(1l);
// service.updateMessDeckService(updatedMessDeckServiceDTO);
// MessDeckServiceInfoDTO updatedMessDeckService =
// service.getMessDeckService(1l);
// assertEquals(updatedMessDeckService, service.getMessDeckService(1l));
//
// }
//
// @Test(expected = MessDeckServiceInfoNotExistException.class)
// @Transactional
// public void testUpdateNonExistingMessDeckService() {
// doThrow(MessDeckServiceInfoNotExistException.class).when(daoMock).update(any(MessDeckServiceInfo.class));
// service.updateMessDeckService(messDeckServiceInfoDTO);
// }
//
// @Test(expected = NotVendorWhoCreatesServiceException.class)
// @Transactional
// public void testUpdateWithVendorOtherThanVendorWhichCreatesService() throws
// Exception {
// doThrow(NotVendorWhoCreatesServiceException.class).when(daoMock).update(messDeckServiceInfo);
//
// service.updateMessDeckService(messDeckServiceInfoDTO);
// }
//
// }
