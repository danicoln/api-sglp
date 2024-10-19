package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Advogado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvogadoRepository extends GenericRepository<Advogado, String> {

    boolean existsByEmail(String email);
}
