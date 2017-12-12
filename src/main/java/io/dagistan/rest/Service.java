package io.dagistan.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Service {

	@GetMapping("/hello")
	public String hello() {
		return "Hello from Spring Boot and Security example...";
	}

}
