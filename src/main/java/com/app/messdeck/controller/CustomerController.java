package com.app.messdeck.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import com.app.messdeck.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<CustomerDTO> getCustomerSummary(@PathVariable long id) {

		CustomerDTO customerSummary = service.getCustomerSummary(id);
		Resource<CustomerDTO> resource = new Resource<CustomerDTO>(customerSummary);
		resource.add(linkTo(methodOn(CustomerController.class).getCustomerSummary(id)).withSelfRel());
		return resource;
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource<CustomerDTO>> createCustomer(@RequestBody CustomerDTO dto,
			HttpServletRequest request) {
		Long customerID = service.createCustomer(dto);
		CustomerDTO customerSummary = service.getCustomerSummary(customerID);
		Resource<CustomerDTO> resource = new Resource<CustomerDTO>(customerSummary);
		resource.add(linkTo(methodOn(CustomerController.class).getCustomerSummary(customerID)).withSelfRel());
		ResponseEntity<Resource<CustomerDTO>> responseEntity = new ResponseEntity<Resource<CustomerDTO>>(resource,
				HttpStatus.CREATED);
		return responseEntity;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCustomer(@PathVariable long id, @RequestBody CustomerDTO dto) {
		dto.setId(id);
		service.updateCustomer(dto);
		CustomerDTO customerSummary = service.getCustomerSummary(id);
		Resource<CustomerDTO> resource = new Resource<CustomerDTO>(customerSummary);
		resource.add(linkTo(methodOn(CustomerController.class).getCustomerSummary(id)).withSelfRel());
		return new ResponseEntity<Object>(resource, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
		service.deleteCustomer(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/{id}/subscribe/messdeckservice/{serviceId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> subscribeService(@PathVariable long id, @PathVariable long serviceId) {

		return new ResponseEntity<Boolean>(service.subScribeMessDeckService(id, serviceId), HttpStatus.OK);

	}

}
