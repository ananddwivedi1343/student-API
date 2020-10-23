package com.anand.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class StudentApiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(StudentApiApplication.class, args);
	}

}
