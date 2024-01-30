package com.qa.lib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.lib.domain.Item;
import com.qa.lib.domain.Person;
import com.qa.lib.repo.ItemRepo;
import com.qa.lib.repo.PersonRepo;

@Service
public class ItemService {

	private ItemRepo repo;
	private PersonRepo personRepo;

	public ItemService(ItemRepo repo, PersonRepo personRepo) {
		super();
		this.repo = repo;
		this.personRepo = personRepo;
	}

	public Item create(Item newItem) {
		return this.repo.save(newItem);
	}

	public List<Item> readAll() {
		return this.repo.findAll();
	}

	public ResponseEntity<Item> read(int id) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(found.get());
	}

	public ResponseEntity<Item> update(int id, Item newItem) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Item existing = found.get();

		existing.setType(newItem.getType());
		existing.setName(newItem.getName());

		Item body = this.repo.save(existing);

		return ResponseEntity.ok(body);

	}

	public ResponseEntity<Object> checkOut(int itemId, int personId) {
		Optional<Item> toCheckOut = this.repo.findById(itemId);

		if (toCheckOut.isEmpty()) {
			return new ResponseEntity<Object>("No item with id: " + itemId, HttpStatus.NOT_FOUND);
		}

		Item existing = toCheckOut.get();

		if (existing.getPerson() != null) {
			return new ResponseEntity<Object>("Item not available", HttpStatus.BAD_REQUEST);
		}

		Optional<Person> customer = this.personRepo.findById(personId);

		if (customer.isEmpty()) {
			return new ResponseEntity<Object>("No person with id: " + personId, HttpStatus.NOT_FOUND);
		}

		existing.setPerson(customer.get());

		Item updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);

	}

	public ResponseEntity<Object> checkIn(int itemId) {
		Optional<Item> toCheckOut = this.repo.findById(itemId);

		if (toCheckOut.isEmpty()) {
			return new ResponseEntity<Object>("No item with id: " + itemId, HttpStatus.NOT_FOUND);
		}

		Item existing = toCheckOut.get();

		existing.setPerson(null);

		Item checkedIn = this.repo.save(existing);

		return ResponseEntity.ok(checkedIn);
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}
}
