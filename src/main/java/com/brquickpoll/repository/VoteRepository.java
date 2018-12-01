package com.brquickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import com.brquickpoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long>{

}
