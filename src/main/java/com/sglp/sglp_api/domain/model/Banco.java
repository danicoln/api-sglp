package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "bancos")
public class Banco {

    private String id;
    private String nome;
    @NotBlank
    private String agencia;
    private String digitoAgencia;
    @NotBlank
    private String conta;
    private String digitoConta;
}
