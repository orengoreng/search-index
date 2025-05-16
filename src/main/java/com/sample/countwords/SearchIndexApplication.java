package com.sample.countwords;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SearchIndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchIndexApplication.class, args);
	}

}
