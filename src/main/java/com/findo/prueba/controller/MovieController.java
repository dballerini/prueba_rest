package com.findo.prueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
	
	@GetMapping("/movies")
	public String list() {
		return "movies";
	}
	
}
