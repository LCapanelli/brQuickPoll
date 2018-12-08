package com.brquickpoll.controller;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brquickpoll.domain.Poll;
import com.brquickpoll.exception.ResourceNotFoundException;
import com.brquickpoll.repository.PollRepository;

@RestController
public class PollController {

	@Inject
	private PollRepository pollRepository; 
	
	//GET method to retrieve all Polls
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls(){
		Iterable<Poll> allPolls = pollRepository.findAll();
		
		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}
	
	//GET specific Poll
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPoll(@PathVariable Long pollId){
		verifyPoll(pollId);
		Optional<Poll> p = pollRepository.findById(pollId);
		
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	//POST to create a new Poll
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll){
		poll = pollRepository.save(poll);
		
		//Set the location HEADER for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders(); 
		URI newPollUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(newPollUri);
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	//PUT to Update a Poll
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
		verifyPoll(pollId);
		Poll p = pollRepository.save(poll);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	//DELETE to delete a Poll
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
		verifyPoll(pollId);
		pollRepository.deleteById(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	protected void verifyPoll(Long pollId) throws ResourceNotFoundException{
		Poll poll = pollRepository.findById(pollId)
				.orElseThrow(() -> new ResourceNotFoundException("Enquete de número " + pollId + " não encontrada!"));
	}
}
