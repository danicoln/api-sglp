package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BancoModel {

    private String id;
    private String nome;
    private String agencia;
    private String digitoAgencia;
    private String conta;
    private String digitoConta;
}
