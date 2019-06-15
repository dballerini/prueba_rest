package com.findo.prueba.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findo.prueba.model.Person;
import com.findo.prueba.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/persons")
@Slf4j
public class PersonApiController {

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
	public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person) {
		return ResponseEntity.ok(personRepository.save(person));
	}

	@PutMapping(value = "")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) {
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
