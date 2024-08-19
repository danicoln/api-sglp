package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Exame;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExameRepository extends MongoRepository<Exame, String> {

    List<Exame> findAllByLaudoId(String laudoId);
}
