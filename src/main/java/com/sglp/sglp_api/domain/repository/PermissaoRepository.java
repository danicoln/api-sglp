package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Permissao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissaoRepository extends MongoRepository<Permissao, String> {
}
