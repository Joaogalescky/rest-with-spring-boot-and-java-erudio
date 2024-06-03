package com.joaogalescky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaogalescky.data.vo.v1.PersonVO;
import com.joaogalescky.services.PersonServices;
import com.joaogalescky.util.MediaType;

@RestController // ResponseBody + Controller
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired // private PersonServices service = new PersonServices();
	private PersonServices service;

	// @RequestMapping setado para GET
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	// @RequestMapping setado para POST
	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

	// @RequestMapping setado para PUT
	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}

	// @RequestMapping setado para DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}