package com.brquickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import com.brquickpoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long>{

	@Query(value="SELECT v.* FROM Option o, Vote v "
			+ "WHERE o.POLL_ID = ?1 AND v.OPTION_ID = o.OPTION_ID", 
			nativeQuery=true)
	public Iterable<Vote> findByPoll(Long pollId);
}
