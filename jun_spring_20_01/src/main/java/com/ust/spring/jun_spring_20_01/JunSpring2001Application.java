package com.ust.spring.jun_spring_20_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class JunSpring2001Application {

	@GetMapping()
	public String print(){
		return "Hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(JunSpring2001Application.class, args);
		System.out.println("It is running.");
	}

}
