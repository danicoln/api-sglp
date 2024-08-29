package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.DadosPerito;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
public class UsuarioInput {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private DadosPeritoInput dadosPerito;
    private boolean ativo;

}
