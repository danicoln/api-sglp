package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Quesito;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuesitoRepository extends MongoRepository<Quesito, String> {
}
