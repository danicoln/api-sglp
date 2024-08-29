package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "dados_perito")
public class DadosPerito extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @CPF
    String cpf;
    @NotBlank
    private String ccm;
    @NotBlank
    private String pis;
    @NotBlank
    private String rg;
    @NotBlank
    @DBRef
    private Banco banco;
    @DBRef
    private Endereco endereco;
}
