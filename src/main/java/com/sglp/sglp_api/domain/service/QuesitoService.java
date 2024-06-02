package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.QuesitoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.repository.QuesitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuesitoService {

    public static final String QUESITO_NAO_ENCONTRADO = "Quesito não encontrado no laudo de ID %s";

    private final QuesitoRepository quesitoRepository;
    private final LaudoPericialService laudoPericialService;
    private final QuesitoTransactionalService transactionalService;

    public List<Quesito> listar(String laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarOuFalhar(laudoId);
        return laudoPericial.getQuesitos();
    }

    public Optional<Quesito> buscar(String quesitoId) {
        return quesitoRepository.findById(quesitoId);
    }

    @Transactional
    public Quesito salvar(String laudoId, Quesito quesito) {
        return transactionalService.salvar(laudoId, quesito);
    }

    public Quesito buscarOuFalhar(String quesitoId) {
        return quesitoRepository.findById(quesitoId)
                .orElseThrow(() -> new QuesitoNaoEncontradoException(quesitoId));
    }

    @Transactional
    public void remover(String laudoId, String quesitoId) {
        LaudoPericial laudo = laudoPericialService.buscarOuFalhar(laudoId);
        List<Quesito> quesitos = laudo.getQuesitos();

        if (quesitos.removeIf(q -> q.getId().equals(quesitoId))) {
            laudoPericialService.atualizar(laudoId, laudo);
            quesitoRepository.deleteById(quesitoId);
        } else {
            throw new QuesitoNaoEncontradoException(QUESITO_NAO_ENCONTRADO, laudoId);
        }
    }

    @Transactional
    public Quesito atualizar(String laudoId, String quesitoId, Quesito quesito) {
        Optional<LaudoPericial> laudoOpt = laudoPericialService.buscar(laudoId);
        Quesito quesitoExistente = buscarOuFalhar(quesitoId);

        if (laudoOpt.isPresent()) {
            quesitoExistente.setParte(quesito.getParte());
            quesitoExistente.setPergunta(quesito.getPergunta());
            quesitoExistente.setResposta(quesito.getResposta());
            return transactionalService.salvar(laudoOpt.get().getId(), quesitoExistente);
        }
        return null;
    }
}
