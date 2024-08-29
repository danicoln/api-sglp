package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
