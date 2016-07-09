package com.app.messdeck.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import com.app.messdeck.annotations.Loggable;
import com.app.messdeck.model.dto.CustomerDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;
import com.app.messdeck.service.MessDeckService;

@RestController
@RequestMapping("/messdeckservice")
public class MessDeckServiceController {

	@Autowired
	MessDeckService messDeckService;

	@Loggable
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<?> createMessDeckService(@RequestBody MessDeckServiceInfoDTO messDeckServiceDTO,
			HttpServletRequest request) {

		MessDeckServiceInfoDTO messDeckServiceInfo = messDeckService.createMessDeckService(messDeckServiceDTO);
		Resource<MessDeckServiceInfoDTO> resource = new Resource<MessDeckServiceInfoDTO>(messDeckServiceInfo);
		resource.add(linkTo(methodOn(MessDeckServiceController.class).getMessDeckService(messDeckServiceInfo.getId()))
				.withSelfRel());
		return resource;
	}

	@Loggable
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public MessDeckServiceInfoDTO getMessDeckService(@PathVariable Long id) {
		return messDeckService.getMessDeckService(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMessDeckService(@RequestBody MessDeckServiceInfoDTO messDeckServiceDTO) {

		MessDeckServiceInfoDTO updateMessDeckService = messDeckService.updateMessDeckService(messDeckServiceDTO);
		Resource<MessDeckServiceInfoDTO> resource = new Resource<MessDeckServiceInfoDTO>(updateMessDeckService);
		resource.add(linkTo(methodOn(MessDeckServiceController.class).getMessDeckService(updateMessDeckService.getId()))
				.withSelfRel());
		return new ResponseEntity<Object>(resource, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMessDeckService(@PathVariable long id) {
		messDeckService.deleteMessDeckService(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@Loggable
	@RequestMapping(value = "/{id}/subscribers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<CustomerDTO> getMessDeckServiceSubscribers(@PathVariable Long id) {
		return messDeckService.getSubscribedCustomers(id);
	}

}
