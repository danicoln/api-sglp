package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.repository.QuesitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class QuesitoTransactionalService {


    private final QuesitoRepository quesitoRepository;
    private final LaudoPericialService laudoPericialService;

    @Transactional
    public Quesito salvar(String laudoId, Quesito quesito) {
        LaudoPericial laudo = laudoPericialService.buscarOuFalhar(laudoId);
        Quesito quesitoSalvo = quesitoRepository.save(quesito);

        laudo.getQuesitos().add(quesitoSalvo);

        laudoPericialService.atualizar(laudoId, laudo);

        return quesitoSalvo;
    }
}
