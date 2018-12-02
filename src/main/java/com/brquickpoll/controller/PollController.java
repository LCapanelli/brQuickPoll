package com.brquickpoll.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brquickpoll.domain.Poll;
import com.brquickpoll.repository.PollRepository;

@RestController
public class PollController {

	@Inject
	private PollRepository pollRepository; 
	
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls(){
		Iterable<Poll> allPolls = pollRepository.findAll(); 
		return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
	}
}
