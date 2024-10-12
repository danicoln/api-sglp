package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

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
    private List<String> permissaoId;

}
