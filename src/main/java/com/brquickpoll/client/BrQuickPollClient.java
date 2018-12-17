package com.brquickpoll.client;

import org.springframework.web.client.RestTemplate;
import com.brquickpoll.domain.Poll;

public class BrQuickPollClient {

	private static final String BRQUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
	private RestTemplate restTemplate = new RestTemplate();
	
	public Poll getPollById(Long pollId) {
		return restTemplate.getForObject(BRQUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);
	}
}
