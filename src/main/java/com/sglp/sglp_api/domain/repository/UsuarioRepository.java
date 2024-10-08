package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    UserDetails findByLogin(String login);
}
