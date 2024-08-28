package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "peritos")
public class Perito extends Pessoa {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @DBRef
    private List<Nomeacao> nomeacoes = new ArrayList<>();
    @DBRef
    private List<LaudoPericial> laudosPericiais = new ArrayList<>();
    private DadosPerito dadosPerito;
    private boolean ativo;

}
