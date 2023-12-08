package com.ust.spring.mainclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MainclassApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainclassApplication.class, args);
		System.out.println("Hello Hai.......");
	}

}
