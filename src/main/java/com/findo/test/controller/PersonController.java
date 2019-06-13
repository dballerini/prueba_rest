package com.findo.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
public class PersonController {
	
	public PersonController() {
	}

	@GetMapping(value = "/")
	public ResponseEntity index() {
		return ResponseEntity.ok("Exito");// ResponseEntity.ok();
	}

	@GetMapping(value = "/person")
	public ResponseEntity getPerson(@RequestParam(value = "id") Long id) {
		return null;// ResponseEntity.ok(itemToReturn);
	}

	@PostMapping(value = "/")
	public ResponseEntity addPerson(@RequestParam(value = "name") String name) {
		return null;// ResponseEntity.ok(myBucketList);
	}

	@PutMapping(value = "/")
	public ResponseEntity updatePerson(@RequestParam(value = "name") String name, @RequestParam(value = "id") Long id) {
		return null;// ResponseEntity.ok(myBucketList);
	}

	@DeleteMapping(value = "/")
	public ResponseEntity removeBucketList(@RequestParam(value = "id") Long id) {
		return null;// ResponseEntity.ok(myBucketList);
	}
}
