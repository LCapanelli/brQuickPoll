package com.brquickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import com.brquickpoll.domain.User;

public interface UserRepository extends CrudRepository <User, Long> {
	public User findByUsername(String username);
}
