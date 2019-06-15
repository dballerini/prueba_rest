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

import com.findo.prueba.model.Movie;
import com.findo.prueba.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/movies")
@Slf4j
public class MovieApiController {

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping(value = "")
	public ResponseEntity<List<Movie>> index() {
		return ResponseEntity.ok(movieRepository.findAll());
	}

	@GetMapping(value = "/movie")
	public ResponseEntity<Movie> getMovie(@RequestParam(value = "id") Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		if (!movie.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(movie.get());
	}

	@PostMapping(value = "")
	public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
		return ResponseEntity.ok(movieRepository.save(movie));
	}

	@PutMapping(value = "")
	public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
		if (!movieRepository.findById(movie.getId()).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(movieRepository.save(movie));
	}

	@DeleteMapping(value = "")
	public ResponseEntity<Movie> removeMovie(@RequestParam(value = "id") Long id) {
		if (!movieRepository.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		movieRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
