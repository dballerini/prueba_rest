package com.findo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findo.test.model.Movie;
import com.findo.test.repository.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
	
    public MovieController(){
    }
    
    @GetMapping(value = "/")
    public Page<Movie> index(Pageable pageable) {
		return movieRepository.findAll(pageable);
    }
    
    @GetMapping(value = "/movie")
    public ResponseEntity getMovie(@RequestParam(value="id") Long id) {
    	return null;//ResponseEntity.ok(itemToReturn);
    }
    @PostMapping(value = "/")
    public ResponseEntity addMovie(@RequestParam(value="name") String name) {
    	return null;// ResponseEntity.ok(myBucketList);
    }
    @PutMapping(value = "/")
    public ResponseEntity updateMovie(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        return null;//ResponseEntity.ok(myBucketList);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removeMovie(@RequestParam(value="id") Long id) {
        return null;//ResponseEntity.ok(myBucketList);
    }
}
