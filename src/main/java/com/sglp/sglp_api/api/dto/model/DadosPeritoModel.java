package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPeritoModel {

    private String id;
    String cpf;
    private String ccm;
    private String pis;
    private String rg;
    private BancoModel banco;
    private EnderecoModel endereco;
}
