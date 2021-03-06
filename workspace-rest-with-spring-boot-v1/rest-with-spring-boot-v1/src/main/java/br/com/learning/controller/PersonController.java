package br.com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.learning.data.vo.PersonVO;
import br.com.learning.services.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping(produces = { "application/json", "application/xml" })
	public List<PersonVO> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		List<PersonVO> lstPersonVO = service.findAll(pageable);
		return lstPersonVO;
	}
	
	@GetMapping(value = "/findPersonByName/{firstName}", 
			produces = { "application/json", "application/xml" })
	public List<PersonVO> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		List<PersonVO> lstPersonVO = service.findPersonByName(firstName, pageable);
		return lstPersonVO;
	}
	
	@GetMapping(value="/{id}", produces = { "application/json", "application/xml" })
	public PersonVO findById(@PathVariable("id") Long id) {	
		return service.findById(id);
	}
	
	@PostMapping(produces = { "application/json", "application/xml" }, 
			consumes = { "application/json", "application/xml" })
	public PersonVO create(@RequestBody PersonVO person) {	
		return service.create(person);
	}
	
	@PutMapping(produces = { "application/json", "application/xml" }, 
			consumes = { "application/json", "application/xml" })
	public PersonVO update(@RequestBody PersonVO person) {	
		return service.update(person);
	}
	
	@PatchMapping(value="/{id}", produces = { "application/json", "application/xml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) {	
		return service.disablePerson(id);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {	
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
