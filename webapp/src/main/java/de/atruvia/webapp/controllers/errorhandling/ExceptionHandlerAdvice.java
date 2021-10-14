package de.atruvia.webapp.controllers.errorhandling;


import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.SchweineServiceException;
import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());
	        body.put("xyz", "abc");
	        // Loggen
	        return ResponseEntity.badRequest().body(body);
	    }

	  @ExceptionHandler(PersonenServiceException.class)
	    public ResponseEntity<Object> handleArrayException(PersonenServiceException ex, WebRequest request) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());
	        body.put("xyz", "abc");
	        log.error("Upps", ex);// Wichtig
	        return ResponseEntity.badRequest().body(body);
	    }

	  @ExceptionHandler(SchweineServiceException.class)
	    public ResponseEntity<Object> handleArrayException(SchweineServiceException ex, WebRequest request) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());
	        body.put("xyz", "abc");
	        log.error("Upps", ex);// Wichtig
	        return ResponseEntity.badRequest().body(body);
	    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        body.put("Hallo", "Welt");


        return ResponseEntity.badRequest().body(body);
    }
    
    
    
    
}
