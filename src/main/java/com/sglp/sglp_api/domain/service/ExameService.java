package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Exame;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExameService {

    private static final String EXAME_NAO_ENCONTRADO = "Exame com o ID %d não encontrado";
    private static final String EXAME_EXISTENTE = "Já existe um exame com ID associado ao laudo pericial %s";

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private LaudoPericialService laudoPericialService;

    public List<Exame> listar(String laudoId) {
        return exameRepository.findAllByLaudoId(laudoId);
    }

    public Exame buscar(String exameId) {
        return this.buscarOuFalhar(exameId);
    }

    @Transactional
    public Exame salvar(String laudoId, Exame exame) {
        LaudoPericial laudo = laudoPericialService.buscarPorIdOuFalhar(laudoId);

        Exame exameSalvo = exameRepository.save(exame);
        laudo.setExame(exameSalvo);
        laudoPericialService.salvar(laudo);
        return exameSalvo;
    }

    public Exame buscarOuFalhar(String exameId) {
        return exameRepository.findById(exameId)
                .orElseThrow(() -> new ExameNaoEncontradoException(exameId));
    }

    public void remover(String laudoId, String exameId) {
        exameRepository.deleteById(exameId);
        removerExameDoLaudoById(laudoId);
    }


    @Transactional
    public Exame atualizar(String laudoId, String exameId, Exame exame) {
        LaudoPericial laudo = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        Exame exameExistente = buscarOuFalhar(exameId);

        if (laudo == null) {
            return null;
        }
        exameExistente.setDescricao(exame.getDescricao());
        exameExistente.setObservacao(exame.getObservacao());
        exameExistente.setTitulo(exame.getTitulo());
        exameExistente.setData(exame.getData());

        atualizaExameNoLaudo(exameExistente, laudoId);

        return salvar(laudo.getId(), exameExistente);
    }

    private void atualizaExameNoLaudo(Exame exame, String laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        if (exame != null) {
            laudoPericial.setExame(exame);
        }
        laudoPericialService.atualizar(laudoId, laudoPericial);
    }

    private void removerExameDoLaudoById(String laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        laudoPericial.setExame(null);
        laudoPericialService.salvar(laudoPericial);
    }

}
