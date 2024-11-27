package com.solo.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class PokerApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PokerApplication.class, args);
	}
	@GetMapping("/")
    public String hello() {
    	return String.format("Hello world");
    }

}
