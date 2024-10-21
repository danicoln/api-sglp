package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    private String id;
    private String nome;
    private String login;
    private String password;
    private boolean ativo;
    private PerfilInput perfil;
}
