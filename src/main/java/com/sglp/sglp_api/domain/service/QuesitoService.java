package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.QuesitoNaoEncontradoException;
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

    public List<Quesito> listar(String laudoId) {
        return quesitoRepository.findAllByLaudoId(laudoId);
    }

    public Optional<Quesito> buscar(String quesitoId) {
        return quesitoRepository.findById(quesitoId);
    }

    @Transactional
    public Quesito salvar(String laudoId, Quesito quesito) {
        quesito.setLaudoId(laudoId);
        return quesitoRepository.save(quesito);
    }

    public Quesito buscarPorIdOuFalhar(String quesitoId) {
        return quesitoRepository.findById(quesitoId)
                .orElseThrow(() -> new QuesitoNaoEncontradoException(quesitoId));
    }

    @Transactional
    public void remover(String quesitoId) {
        quesitoRepository.deleteById(quesitoId);
    }

    @Transactional
    public Quesito atualizar(String quesitoId, Quesito quesito) {
        Quesito quesitoAtualizado = buscarPorIdOuFalhar(quesitoId);
        quesitoAtualizado.setParte(quesito.getParte());
        quesitoAtualizado.setPergunta(quesito.getPergunta());
        quesitoAtualizado.setResposta(quesito.getResposta());

        return quesitoAtualizado;
    }
}
