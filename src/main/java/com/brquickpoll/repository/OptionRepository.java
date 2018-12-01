package com.brquickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import com.brquickpoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long>{

}
