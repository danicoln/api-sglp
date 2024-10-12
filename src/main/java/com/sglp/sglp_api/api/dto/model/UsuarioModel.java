package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

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
    private List<String> permissaoId;

}
