package com.sglp.sglp_api.domain.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "perfis")
public class Perfil extends AbstractEntity {

    @Id
    private String id;
    private String nome;
    private List<Permissao> permissoes;
}
