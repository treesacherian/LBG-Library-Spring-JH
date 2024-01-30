package com.qa.lib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.lib.domain.Person;
import com.qa.lib.repo.PersonRepo;

@Service
public class PersonService {

	private PersonRepo repo;

	public PersonService(PersonRepo repo) {
		super();
		this.repo = repo;
	}

	public Person create(Person newPerson) {
		return this.repo.save(newPerson);
	}

	public List<Person> readAll() {
		return this.repo.findAll();
	}

	public ResponseEntity<Person> read(int id) {
		Optional<Person> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(found.get());
	}

	public ResponseEntity<Person> update(int id, Person newPerson) {
		Optional<Person> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Person existing = found.get();

		existing.setName(newPerson.getName());

		Person body = this.repo.save(existing);

		return ResponseEntity.ok(body);

	}

	public boolean delete(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}
}
