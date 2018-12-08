package com.brquickpoll.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.brquickpoll.dto.error.ErrorDetail;
import com.brquickpoll.dto.error.ValidationError;
import com.brquickpoll.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException methArgNotValid, HttpServletRequest req){
		
		ErrorDetail errorDetail = new ErrorDetail();
		//Title: brief title for the error 
		errorDetail.setTitle("Validation Failed");
		//Status: HTTP status code 
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		//Detail: Short description of the error to an end user
		errorDetail.setDetail("Input Validatoin Failed");
		//Time Stamp: time in milliseconds when the error has occurred
		errorDetail.setTimeStamp(new Date().getTime());
		//devMessage: tech info for devs	
		errorDetail.setDevMessage(methArgNotValid.getClass().getName());
		//URI error
		String requestPath = (String) req.getAttribute("javax.servlet.error.request_uri");
			if (requestPath == null) {
				requestPath = req.getRequestURI();
			}
			
		//Creating ValidationError instances
		List<FieldError> fieldErrors = methArgNotValid.getBindingResult().getFieldErrors();
			for(FieldError f : fieldErrors) {
				List<ValidationError> validationErrorList = errorDetail.getErrors().get(f.getField());
				if (validationErrorList == null) {
					validationErrorList = new ArrayList<ValidationError>();
					errorDetail.getErrors().put(f.getField(),
							validationErrorList);
				}
			ValidationError validationError = new ValidationError();
			validationError.setCode(f.getCode());
			validationError.setMessage(f.getDefaultMessage());
			validationErrorList.add(validationError);
			}
			
		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);	
	}
	
	@Deprecated
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException res, HttpServletRequest req){
		
		ErrorDetail errorDetail = new ErrorDetail(); 
		//Title: brief title for the error 
		errorDetail.setTitle("Resource Not Found");
		
		//Status: HTTP status code 
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		
		//Detail: Short description of the error to an end user
		errorDetail.setDetail(res.getMessage());
		
		//Time Stamp: time in milliseconds when the error has occurred
		errorDetail.setTimeStamp(new Date().getTime());
		
		//devMessage: tech info for devs
		errorDetail.setDevMessage(res.getClass().getName());
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}
}
