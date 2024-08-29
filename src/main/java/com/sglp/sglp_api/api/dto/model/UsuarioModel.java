package com.sglp.sglp_api.api.dto.model;

import com.sglp.sglp_api.domain.model.DadosPerito;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
public class UsuarioModel {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private DadosPeritoModel dadosPerito;
    private boolean ativo;

}
