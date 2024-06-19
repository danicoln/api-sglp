package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class LaudoPericialInput {

    private String id;
    private String objetivo;
    private String metodologiaAplicada;
    private String conclusao;
    private String introducao;
    private OffsetDateTime dataDoLaudo;
    private String historico;
    private ExameDaMateriaInput exameDaMateria;
    private List<QuesitoInput> quesitos;
    private boolean ativo;
    private String status;
    private ProcessoInput processo;
}
