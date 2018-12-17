package com.brquickpoll.client;

import org.springframework.web.client.RestTemplate;
import com.brquickpoll.domain.Poll;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

public class BrQuickPollClient {

	private static final String BRQUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
	private RestTemplate restTemplate = new RestTemplate();
	
	public Poll getPollById(Long pollId) {
		return restTemplate.getForObject(BRQUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);
	}
	
	public List<Poll> getAllPolls() {
		ParameterizedTypeReference<List<Poll>> responseType = new ParameterizedTypeReference <List<Poll>>() {};
		ResponseEntity<List<Poll>> responseEntity = restTemplate.exchange(BRQUICK_POLL_URI_V1, HttpMethod.GET, 
				null, responseType);
		List<Poll> allPolls = responseEntity.getBody();
		
		return allPolls;
	}
}
