package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "usuarios")
public class Usuario extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nome;
    @Email
    private String email;
    private String senha;
    @DBRef
    private DadosPerito dadosPerito;
    private boolean ativo;

}
