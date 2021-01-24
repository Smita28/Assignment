package com.org.recipe;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is the main class of this application.
 * 
 * @author smita
 *
 */

@SpringBootApplication
@EnableSwagger2
public class RecipeManagementApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RecipeManagementApplication.class, args);
	}
	/**
	 * This is to map objects automatically from entity class to DTO class.
	 *
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
