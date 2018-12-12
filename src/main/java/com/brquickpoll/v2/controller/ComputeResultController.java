package com.brquickpoll.v2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.brquickpoll.domain.Vote;
import com.brquickpoll.dto.OptionCount;
import com.brquickpoll.dto.VoteResult;
import com.brquickpoll.repository.VoteRepository;
import com.wordnik.swagger.annotations.Api;

@RestController("computeResultControllerV2")
@RequestMapping("/v2/")
@Api(value="results", description="Compute Results API")
public class ComputeResultController {

	@Inject
	private VoteRepository voteRepository;
	
	@RequestMapping(value="/computeresult", method=RequestMethod.GET)
	public ResponseEntity<?> computeResult(@RequestParam Long pollId){
		VoteResult voteResult = new VoteResult();
		
		Iterable<Vote> allVotes = voteRepository.findByPoll(pollId); 
		
		int totalVotes = 0; 
		Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>(); 
		
		for(Vote v : allVotes) {
			totalVotes++; 
			OptionCount oc = tempMap.get(v.getOption().getId()); 
			
			if (oc == null) {
				oc = new OptionCount();
				oc.setOptionId(v.getOption().getId());
				tempMap.put(v.getOption().getId(), oc);
			}
			oc.setCount(oc.getCount()+1);
		}
		
		voteResult.setTotalVotes(totalVotes);
		voteResult.setResults(tempMap.values());
		
		return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK); 
	}
}
