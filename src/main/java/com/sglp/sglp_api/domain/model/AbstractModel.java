package com.sglp.sglp_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractModel {

    private String loginInclusao;
    private String loginAtualizacao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
}
