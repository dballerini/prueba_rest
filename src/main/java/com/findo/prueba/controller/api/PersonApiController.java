package com.findo.prueba.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.findo.prueba.model.Movie;
import com.findo.prueba.model.Person;
import com.findo.prueba.repository.PersonRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/persons")
@Slf4j
@Api(value = "CRUD Persons", description = "Manejador de Personas")
public class PersonApiController {

	@Autowired
	private PersonRepository personRepository;

	@ApiOperation(value = "Lista todas las personas", response = List.class)
	@GetMapping(value = "")
	public ResponseEntity<List<Person>> index() {
		return ResponseEntity.ok(personRepository.findAll());
	}

	@ApiOperation(value = "Busca la persona por Id", response = List.class)
	@GetMapping(value = "/person/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(person.get());
	}

	@ApiOperation(value = "Guarda la pelicula", response = Movie.class)
	@PostMapping(value = "")
	public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person) {
		return ResponseEntity.ok(personRepository.save(person));
	}

	@ApiOperation(value = "Actualiza la persona", response = Movie.class)
	@PutMapping(value = "")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) {
		if (!personRepository.findById(person.getId()).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(personRepository.save(person));
	}

	@ApiOperation(value = "Borra la persona", response = Movie.class)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Person> removePerson(@ApiParam(value = "Id de la persona", required = true) @PathVariable Long id) {
		if (!personRepository.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		personRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
