package com.sglp.sglp_api.api.dto.model;

import com.sglp.sglp_api.domain.model.Parte;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuesitoModel {

    private String id;
    private String parte;
    private String pergunta;
    private String resposta;
}
