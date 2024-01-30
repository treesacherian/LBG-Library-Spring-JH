package com.qa.lib.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lib.domain.Person;
import com.qa.lib.service.PersonService;

@RestController
@RequestMapping("/person")
@CrossOrigin
public class PersonController {

	private PersonService service;

	public PersonController(PersonService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public Person create(@RequestBody Person newPerson) {
		return this.service.create(newPerson);
	}

	@GetMapping("/get")
	public List<Person> readAll() {
		return this.service.readAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Person> read(@PathVariable int id) {
		return this.service.read(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person newPerson) {
		return this.service.update(id, newPerson);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}
}
