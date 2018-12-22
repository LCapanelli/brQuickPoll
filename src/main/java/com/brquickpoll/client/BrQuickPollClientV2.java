package com.brquickpoll.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.brquickpoll.domain.Option;
import com.brquickpoll.domain.Poll;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

/***
 * 
 * @author Lucas Capanelli
 * 
 * REST Client using the Spring web rest template
 *
 */
public class BrQuickPollClientV2 {

	private static final String BRQUICK_POLL_URI_V2 = "http://localhost:8080/v2/polls";
	private RestTemplate restTemplate = new RestTemplate();
	
	public Poll getPollById(Long pollId) {
		return restTemplate.getForObject(BRQUICK_POLL_URI_V2 + "/{pollId}", Poll.class, pollId);
	}
	
	public PageWrapper<Poll> getAllPolls(int page, int size) {
		ParameterizedTypeReference<PageWrapper<Poll>> responseType = new ParameterizedTypeReference <PageWrapper<Poll>>() {};
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(BRQUICK_POLL_URI_V2)
				.queryParam("page", page)
				.queryParam("size", size);
		
		ResponseEntity<PageWrapper<Poll>> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
		
		return responseEntity.getBody();
	}
	
	public URI createPoll(Poll poll) {
		return restTemplate.postForLocation(BRQUICK_POLL_URI_V2, poll);
	}
	
	public void updatePoll(Poll poll) {
		restTemplate.put(BRQUICK_POLL_URI_V2 + "/{pollId}",  poll, poll.getId()); 
	}
	
	public void deletePoll(Long pollId) {
		restTemplate.delete(BRQUICK_POLL_URI_V2 + "/{pollId}",  pollId);
	}
	
	public static void main(String[] args) {
		BrQuickPollClientV2 client = new BrQuickPollClientV2();
		
		Poll newPoll = new Poll();
		newPoll.setQuestion("Qual sua cor favorita?");
		Set<Option> options = new HashSet<>();
		newPoll.setOptions(options);
		
		Option option1 = new Option(); option1.setValue("Red"); options.add(option1);
		Option option2 = new Option(); option2.setValue("Blue");options.add(option2);
		Option option3 = new Option(); option3.setValue("Blue");options.add(option3);
		Option option4 = new Option(); option4.setValue("Blue");options.add(option4);
		URI pollLocation = client.createPoll(newPoll);
		
		System.out.println("Newly Created Poll Location " + pollLocation);
	}

}
