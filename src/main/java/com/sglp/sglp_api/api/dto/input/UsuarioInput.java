package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.api.dto.model.GenericId;
import com.sglp.sglp_api.domain.model.Perfil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioInput {

    private String id;
    private String nome;
    private String login;
    private String senha;
    private boolean ativo;
    private PerfilInput perfil;
}
