package com.app.messdeck.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.VendorDTO;
import com.app.messdeck.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorService service;

	public VendorController() {

	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<VendorDTO> getVendorSummary(@PathVariable Long id) {

		VendorDTO vendorSummaryDTO = service.getVendorSummary(id);
		Resource<VendorDTO> resource = new Resource<>(vendorSummaryDTO);
		resource.add(linkTo(methodOn(VendorController.class).getVendorSummary(id)).withSelfRel());

		return resource;

	}

	@RequestMapping(value = "/{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<VendorDTO> getVendorDetails(@PathVariable Long id) {

		VendorDTO dto = service.getVendorDetails(id);
		Resource<VendorDTO> resource = new Resource<>(dto);
		resource.add(linkTo(methodOn(VendorController.class).getVendorDetails(id)).withSelfRel());

		return resource;

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createVendor(@RequestBody VendorDTO dto, HttpServletRequest request) {
		System.out.println("Vendor Controller : Vendor DTO revied = " + dto);
		Long vendorID = service.createVendor(dto);
		String resourceUrl = request.getRequestURL().toString() + "/" + vendorID;
		ResponseEntity<?> responseEntity = new ResponseEntity<String>(resourceUrl, HttpStatus.CREATED);
		return responseEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateVendor(@PathVariable long id, @RequestBody VendorDTO dto) {
		dto.setId(id);
		service.updateVendor(dto);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVendor(@PathVariable long id) {
		service.deleteVendor(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Resource<VendorDTO>> getAllVendorsSummary() {

		List<VendorDTO> allVendorsSummary = service.getAllVendorsSummary();
		List<Resource<VendorDTO>> resourceList = new ArrayList<>();

		for (VendorDTO dto : allVendorsSummary) {
			Resource<VendorDTO> resource = new Resource<>(dto);
			resource.add(linkTo(methodOn(VendorController.class).getVendorSummary(dto.getId())).withSelfRel());
			resource.add(linkTo(methodOn(VendorController.class).getVendorDetails(dto.getId())).withRel("details"));
			resourceList.add(resource);
		}

		return resourceList;

	}

	@RequestMapping(value = "/{id}/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDTO> getCustomers(@PathVariable long id) {

		return service.getCustomrs(id);

	}

}
