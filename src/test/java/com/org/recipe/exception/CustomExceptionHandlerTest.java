package com.org.recipe.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.WebRequest;

/**
 * This class is responsible for testing custom exception scenarios.
 * @author smita
 *
 */
@ExtendWith(SpringExtension.class)
class CustomExceptionHandlerTest {

	@MockBean
	CustomExceptionHandler customExceptionHandler;
	@MockBean
	WebRequest request;
	/**
	 * This method explains a simple test for Invalid Input Exception
	 */
	@Test
	void testHandleInvalidInputException() {
		InvalidInputException ex = new InvalidInputException();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		customExceptionHandler.handleInvalidInputException(ex, request);
		
	}
	/**
	 * This method explains a simple test for Recipe Not Found Exception
	 */
	@Test
	void handleRecipeNotFoundExceptionTest() {
		RecipeNotFoundException ex = new RecipeNotFoundException();
		assertEquals(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND);
		customExceptionHandler.handleRecipeNotFoundException(ex, request);
		
	}
	/**
	 * This method explains a simple test for Recipe Already Exists Exception
	 */
	@Test
	void testRecipeAlreadyExistsException() {
		RecipeAlreadyExistsException ex = new RecipeAlreadyExistsException();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		customExceptionHandler.handleRecipeAlreadyExistsException(ex, request);
		
	}
	/**
	 * This method explains a simple test for all other type Exception.
	 */
	@Test
	void handleAllExceptionTest() {
		Exception ex = new Exception();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		customExceptionHandler.handleAllExceptions(ex, request);
		
	}

}
