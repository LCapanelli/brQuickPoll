package com.brquickpoll.handler;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.brquickpoll.dto.error.ErrorDetail;
import com.brquickpoll.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

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
		errorDetail.setDevMessage(res.getMessage().getClass().getName());
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}
}
