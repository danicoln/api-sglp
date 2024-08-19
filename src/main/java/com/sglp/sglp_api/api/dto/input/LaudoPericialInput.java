package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class LaudoPericialInput {

    private String id;
    private String objetivo;
    private String metodologiaAplicada;
    private String conclusao;
    private String introducao;
    private LocalDateTime dataDoLaudo;
    private String historico;
    private ExameInput exameDaMateria;
    private List<QuesitoInput> quesitos;
    private boolean ativo;
    private String status;
    private ProcessoInput processo;
}
