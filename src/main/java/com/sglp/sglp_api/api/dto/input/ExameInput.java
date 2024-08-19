package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExameInput {

    private String id;
    private String observacao;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private String laudoId;
}
