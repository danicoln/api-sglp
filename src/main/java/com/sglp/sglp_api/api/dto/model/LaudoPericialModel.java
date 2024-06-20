package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class LaudoPericialModel {

    private String id;
    private String objetivo;
    private String metodologiaAplicada;
    private String introducao;
    private OffsetDateTime dataDoLaudo;
    private String conclusao;
    private String historico;
    private ExameDaMateriaModel exameDaMateria;
    private List<QuesitoModel> quesitos;
    private boolean ativo;
    private String status;
    private ProcessoModel processo;
    private String numero;
}
