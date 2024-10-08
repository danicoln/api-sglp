package com.sglp.sglp_api.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BancoInput {

    private String id;
    private String nome;
    private String agencia;
    private String digitoAgencia;
    private String conta;
    private String digitoConta;
}
