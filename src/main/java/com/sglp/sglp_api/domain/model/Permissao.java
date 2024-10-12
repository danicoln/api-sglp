package com.sglp.sglp_api.domain.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "permissao")
public class Permissao extends AbstractEntity {

    @Id
    private String id;
    private String descricao;
}
