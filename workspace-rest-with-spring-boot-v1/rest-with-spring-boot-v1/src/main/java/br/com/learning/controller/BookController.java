package br.com.learning.controller;

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

import br.com.learning.data.vo.BookVO;
import br.com.learning.services.BookService;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(produces = {"application/json", "application/xml"})
	public List<BookVO> findAll(){
		return service.findAll();
	}
	
	@PostMapping(produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public BookVO create(@RequestBody BookVO book) {
		return service.create(book);
	}
	
	@GetMapping(value = "/{id}", 
			produces = {"application/json", "application/xml"})
	public BookVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PutMapping(produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public BookVO update(@RequestBody BookVO book) {
		return service.update(book);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
