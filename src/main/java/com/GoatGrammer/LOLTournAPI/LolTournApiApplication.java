package com.GoatGrammer.LOLTournAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class LolTournApiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LolTournApiApplication.class, args);
	}
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, World!";
	}

}
