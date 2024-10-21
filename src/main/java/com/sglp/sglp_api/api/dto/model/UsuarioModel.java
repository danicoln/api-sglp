package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioModel {

    private String id;
    private String nome;
    private String login;
    private boolean ativo;
    private String password;
    private PerfilModel perfil;

    private String loginInclusao;
    private String loginAtualizacao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
}
