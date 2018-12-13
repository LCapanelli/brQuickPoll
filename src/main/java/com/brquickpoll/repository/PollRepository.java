package com.brquickpoll.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.brquickpoll.domain.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long>{

}
