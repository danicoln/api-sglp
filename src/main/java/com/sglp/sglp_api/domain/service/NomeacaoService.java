package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.NomeacaoNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Nomeacao;
import com.sglp.sglp_api.domain.model.Processo;
import com.sglp.sglp_api.domain.repository.NomeacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NomeacaoService {

    @Autowired
    private NomeacaoRepository nomeacaoRepository;

    @Autowired
    private ProcessoService processoService;

    public List<Nomeacao> listar() {
        return nomeacaoRepository.findAll();
    }

    public Nomeacao buscarOuFalhar(String nomeacaoId) {
        return nomeacaoRepository.findById(nomeacaoId)
                .orElseThrow(() -> new NomeacaoNaoEncontradaException(nomeacaoId));
    }

    @Transactional
    public Nomeacao salvar(Nomeacao nomeacao) {
        Processo processo = processoService.buscarPorIdOuFalhar(nomeacao.getProcesso().getId());

        return nomeacaoRepository.save(nomeacao);
    }

    public void remover(String nomeacaoId) {
        nomeacaoRepository.deleteById(nomeacaoId);
    }
}
