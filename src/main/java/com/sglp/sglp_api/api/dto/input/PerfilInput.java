package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilInput {

    private String id;
    private String nome;
    private List<PermissaoInput> permissoes;
}
