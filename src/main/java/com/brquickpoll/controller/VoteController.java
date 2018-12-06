package com.brquickpoll.controller;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brquickpoll.domain.Vote;
import com.brquickpoll.repository.VoteRepository;;

@RestController
public class VoteController {

	@Inject
	private VoteRepository voteRepository; 
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
	public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote){
		vote = voteRepository.save(vote); 
		
		//Setting Headers for the newly create resource
		HttpHeaders responseHeaders = new HttpHeaders(); 
		responseHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(vote.getId())
				.toUri());
		
		return new ResponseEntity<> (null, responseHeaders, HttpStatus.CREATED); 
	}
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
	public Iterable<Vote> getAllVotes(@PathVariable Long pollId){
		return voteRepository.findByPoll(pollId);
	}
}