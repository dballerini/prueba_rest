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
@RequestMapping("movies")
public class MovieController {

    public MovieController(){
    }
    
    @GetMapping(value = "/")
    public ResponseEntity index() {
    	return ResponseEntity.ok("Exito");//ResponseEntity.ok();
    }
    
    @GetMapping(value = "/movie")
    public ResponseEntity getBucket(@RequestParam(value="id") Long id) {
    	return null;//ResponseEntity.ok(itemToReturn);
    }
    @PostMapping(value = "/")
    public ResponseEntity addToBucketList(@RequestParam(value="name") String name) {
    	return null;// ResponseEntity.ok(myBucketList);
    }
    @PutMapping(value = "/")
    public ResponseEntity updateBucketList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        return null;//ResponseEntity.ok(myBucketList);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removeBucketList(@RequestParam(value="id") Long id) {
        return null;//ResponseEntity.ok(myBucketList);
    }
}
