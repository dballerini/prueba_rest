package com.findo.prueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
	
	@GetMapping("/persons")
	public String list() {
		return "persons";
	}
	
}
