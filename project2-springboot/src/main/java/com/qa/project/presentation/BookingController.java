package com.qa.project.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.project.business.BookingService;
import com.qa.project.persistence.domain.BookingDomain;
import com.qa.project.business.dto.BookingDTO;

/*
 * This BookingController class is a Spring REST controller that maps HTTP requests 
 * to methods that perform CRUD operations on Booking entities using BookingService class.
 */
@CrossOrigin
@RestController
@RequestMapping("/booking") // end-point at http://localhost:port/booking
public class BookingController {
	
	// mapping URLs to Methods
	
	// dependencies
	private BookingService service;
	
	// constructor
	@Autowired // grab the object from the beanbag
	public BookingController(BookingService service) {
		super();
		this.service = service;
	}
	
	// CRUD Functionality

	// CREATE
	@PostMapping // signifies that this method will handle POST requests to the specified path
	public ResponseEntity<BookingDTO> create(@RequestBody BookingDomain model) {
		return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	}

	// READ
	@GetMapping// signifies that this method will handle GET requests to the specified path
	public ResponseEntity<List<BookingDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/{id}") // signifies that this method will handle GET requests to the specified path
	public ResponseEntity<BookingDTO> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// UPDATE - PUT (REPLACE ALL)
	@PutMapping("/{id}") // signifies that this method will handle PUT requests to the specified path
	public ResponseEntity<BookingDTO> update(@PathVariable Long id, @RequestBody BookingDomain model) {
		return new ResponseEntity<>(this.service.update(id, model), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("/{id}") // signifies that this method will handle DELETE requests to the specified path
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return new ResponseEntity<>(this.service.delete(id) ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
