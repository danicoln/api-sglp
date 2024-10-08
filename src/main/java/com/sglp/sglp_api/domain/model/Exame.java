package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "exames")
public class Exame extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String observacao;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private String laudoId;
}
