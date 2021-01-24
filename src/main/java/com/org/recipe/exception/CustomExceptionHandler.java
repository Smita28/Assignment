package com.org.recipe.exception;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 /**
  * This class handles exception of entire application on global level.
  * @author smita
  *
  */
@ControllerAdvice(basePackages = "com.org.recipe")
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    
    /**
     * This is custom exception method to handle invalid input.
     * @param InvalidInputException
     * @param WebRequest
     * @return ResponseEntity<ErrorResponse>
     */
 
    @ExceptionHandler(value = {InvalidInputException.class})
    public final ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Invalid Input", details);
        logger.error("Invalid Input Exception: ",ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * This method is to handle recipe not found exception.
     * @param RecipeNotFoundException
     * @param WebRequest
     * @return ResponseEntity<ErrorResponse>
     */
 
    @ExceptionHandler(value = {RecipeNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleRecipeNotFoundException(RecipeNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Could not find recipe", details);
        logger.error("Could not find recipe Exception: ",ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    /**
     * This is custom exception method to handle recipe already exists.
     * @param RecipeAlreadExistsException
     * @param WebRequest
     * @return ResponseEntity<ErrorResponse>
     */
 
    @ExceptionHandler(value = {RecipeAlreadExistsException.class})
    public final ResponseEntity<ErrorResponse> handleRecipeAlreadExistsException(RecipeAlreadExistsException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Could not find recipe", details);
        logger.error("Could not find recipe Exception: ",ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * This method is for handling all kind of exception.
     * @param Exception
     * @param WebRequest
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        logger.error("Exception: ",ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
  
}

