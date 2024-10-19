package com.sglp.sglp_api.api.dto.model;

import com.sglp.sglp_api.domain.model.PermissaoModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilModel {

    private String id;
    private String nome;
    private List<PermissaoModel> permissoes;
}
