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
import com.findo.prueba.repository.MovieRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/movies")
@Slf4j
@Api(value = "CRUD Movies", description = "Manejador de Peliculas")
public class MovieApiController {

	@Autowired
	private MovieRepository movieRepository;

	@ApiOperation(value = "Lista todas las peliculas", response = List.class)
	@GetMapping(value = "")
	public ResponseEntity<List<Movie>> index() {
		return ResponseEntity.ok(movieRepository.findAll());
	}

	@ApiOperation(value = "Busca la pelicula por Id", response = List.class)
	@GetMapping(value = "/movie/{id}")
	public ResponseEntity<Movie> getMovie(@ApiParam(value = "Id de la pelicula", required = true) @PathVariable Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		if (!movie.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(movie.get());
	}

	@ApiOperation(value = "Guarda la pelicula", response = Movie.class)
	@PostMapping(value = "")
	public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
		return ResponseEntity.ok(movieRepository.save(movie));
	}

	@ApiOperation(value = "Actualiza la pelicula", response = Movie.class)
	@PutMapping(value = "")
	public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
		if (!movieRepository.findById(movie.getId()).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(movieRepository.save(movie));
	}

	@ApiOperation(value = "Borra la pelicula", response = Movie.class)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Movie> removeMovie(@ApiParam(value = "Id de la pelicula", required = true) @PathVariable Long id) {
		if (!movieRepository.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		movieRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
