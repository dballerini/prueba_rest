package com.findo.prueba.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findo.prueba.model.Person;
import com.findo.prueba.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping(value = "")
	public ResponseEntity<List<Person>> index() {
		return ResponseEntity.ok(personRepository.findAll());
	}

	@GetMapping(value = "/person")
	public ResponseEntity<Person> getPerson(@RequestParam(value = "id") Long id) {
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(person.get());
	}

	@PostMapping(value = "")
	public ResponseEntity<Person> addPerson(@RequestParam(value = "person") Person person) {
		return ResponseEntity.ok(personRepository.save(person));
	}

	@PutMapping(value = "")
	public ResponseEntity<Person> updatePerson(@RequestParam(value = "person") Person person) {
		if (!personRepository.findById(person.getId()).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(personRepository.save(person));
	}

	@DeleteMapping(value = "")
	public ResponseEntity<Person> removePerson(@RequestParam(value = "id") Long id) {
		if (!personRepository.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		personRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
