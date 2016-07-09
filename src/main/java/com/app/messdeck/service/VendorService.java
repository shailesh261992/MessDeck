package com.app.messdeck.service;

import java.util.List;

import com.app.messdeck.businessException.VendorNotExistException;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.VendorDTO;

public interface VendorService {

	VendorDTO getVendorSummary(Long id) throws VendorNotExistException;

	VendorDTO getVendorDetails(Long id) throws VendorNotExistException;

	Long createVendor(VendorDTO vendorDTO);

	void updateVendor(VendorDTO dto);

	void deleteVendor(Long id);

	List<VendorDTO> getAllVendorsSummary();

	List<CustomerDTO> getCustomrs(Long id);

}
