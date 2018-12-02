package com.brquickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import com.brquickpoll.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long>{

}
