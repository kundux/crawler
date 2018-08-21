package com.buildit.webcrawler.error;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.buildit.webcrawler.controller.CrawlController;
import com.buildit.webcrawler.model.GeneralResponse;

/**
 * This class handle exceptions and errors for crawler.
 * @author DELL
 *
 */
@ControllerAdvice(assignableTypes = CrawlController.class)
public class ErrorHandler extends ResponseEntityExceptionHandler {
	private static Logger LOGGER = LogManager.getLogger(ErrorHandler.class);
	private GeneralResponse response = new GeneralResponse();
	
	/**
	 * Handles URL specific exceptions 
	 * @param e IllegalArgumentException Class
	 * @return Response Entity Object
	 */
	@ExceptionHandler(value = { IllegalArgumentException.class})
	public ResponseEntity<Object> malformedUrlError(final IllegalArgumentException e) {
		return error(e, HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Handles document parsing exceptions 
	 * @param e IOException Class
	 * @return Response Entity Object
	 */
	@ExceptionHandler(value = { IOException.class})
	public ResponseEntity<Object> internalException(final IOException e) {
		return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Common method to build final error response
	 * @param exception Exception Object
	 * @param httpStatus HttpStatus Object
	 * @return
	 */
	private ResponseEntity<Object> error(final Exception exception, final HttpStatus httpStatus) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		response.setMessage(message);
		response.setErrorCode(httpStatus.value());
		
		LOGGER.error("Error occured ", exception.getMessage());
		
		return new ResponseEntity<>(response, httpStatus);
	}

}
