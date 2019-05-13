package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.Example;

@RestController
public class TestController {
	
	@Example("test")
	@GetMapping("{name}")
	public String get(@PathVariable("name") String name) {
		return name;
	}
	
}
