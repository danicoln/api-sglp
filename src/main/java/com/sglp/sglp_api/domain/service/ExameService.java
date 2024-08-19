package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameNaoEncontradoException;
import com.sglp.sglp_api.domain.exception.ExameExistenteException;
import com.sglp.sglp_api.domain.model.Exame;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Exame buscarPorId(String laudoId, String exameId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        Optional<Exame> exameOptional = exameRepository.findById(exameId);

        return exameOptional.get();
    }

    @Transactional
    public Exame atualizar(String laudoId, String exameId, Exame exame) {
        Optional<LaudoPericial> laudoOptional = laudoPericialService.buscar(laudoId);
        Exame exameExistente = buscarOuFalhar(exameId);

        if (laudoOptional.isPresent()) {
            exameExistente.setDescricao(exame.getDescricao());
            exameExistente.setObservacao(exame.getObservacao());
            exameExistente.setTitulo(exame.getTitulo());
            exameExistente.setData(exame.getData());
            return salvar(laudoOptional.get().getId(), exameExistente);

        }
        return null;
    }

    private void removerExameDoLaudoById(String laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        laudoPericial.setExame(null);
        laudoPericialService.salvar(laudoPericial);
    }

}
