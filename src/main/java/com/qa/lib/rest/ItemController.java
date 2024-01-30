package com.qa.lib.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lib.domain.Item;
import com.qa.lib.service.ItemService;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public Item create(@RequestBody Item newItem) {
		return this.service.create(newItem);
	}

	@GetMapping("/get")
	public List<Item> readAll() {
		return this.service.readAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Item> read(@PathVariable int id) {
		return this.service.read(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Item> update(@PathVariable int id, @RequestBody Item newItem) {
		return this.service.update(id, newItem);
	}

	@PatchMapping("/checkOut/{itemId}/{personId}")
	public ResponseEntity<Object> checkOut(@PathVariable int itemId, @PathVariable int personId) {
		return this.service.checkOut(itemId, personId);
	}

	@PatchMapping("/checkIn/{itemId}")
	public ResponseEntity<Object> checkIn(@PathVariable int itemId) {
		return this.service.checkIn(itemId);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}
}
