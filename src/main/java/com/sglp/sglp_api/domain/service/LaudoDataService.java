package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.model.Exame;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.Quesito;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LaudoDataService {

    private final LaudoPericialService laudoPericialService;
    private final QuesitoService quesitoService;
    private final ExameService exameService;
    private final AdvogadoService advogadoService;
    private final NomeacaoService nomeacaoService;
    private final ProcessoService processoService;

    public LaudoPericial findLaudoPericialById(String id) {
        return laudoPericialService.buscarPorIdOuFalhar(id);
    }

    public Quesito findQuesitoById(String id) {
        return quesitoService.buscarPorIdOuFalhar(id);
    }

    public Exame findExameDaMateriaById(String id) {
        return exameService.buscarOuFalhar(id);
    }

}
