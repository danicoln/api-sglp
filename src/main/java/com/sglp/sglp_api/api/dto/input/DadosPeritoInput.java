package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPeritoInput {

    private String id;
    String cpf;
    private String ccm;
    private String pis;
    private String rg;
    private BancoInput banco;
    private EnderecoInput endereco;
}
