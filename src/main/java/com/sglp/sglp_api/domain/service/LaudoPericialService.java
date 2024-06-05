package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.LaudoPericialNaoEncontradoException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.repository.LaudoPericialRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaudoPericialService {

    public static final String LAUDO_PERICIAL_NAO_ENCONTRADO = "Laudo Pericial com o ID %s não encontrado";

    private final LaudoPericialRepository laudoPericialRepository;

    @Transactional
    public LaudoPericial salvar(LaudoPericial laudoPericial) {
        return laudoPericialRepository.save(laudoPericial);
    }

    public List<LaudoPericial> listar() {
        return laudoPericialRepository.findAll();
    }

    public LaudoPericial buscarPorIdOuFalhar(String laudoId) {
        return laudoPericialRepository.findById(laudoId)
                .orElseThrow(() -> new LaudoPericialNaoEncontradoException(laudoId));
    }

    public Optional<LaudoPericial> buscar(String laudoId) {
        return this.laudoPericialRepository.findById(laudoId);
    }

    @Transactional
    public LaudoPericial atualizar(String laudoId, LaudoPericial laudo) {
        LaudoPericial laudoExistente = buscarPorIdOuFalhar(laudoId);
        BeanUtils.copyProperties(laudo, laudoExistente, "id");
        return laudoPericialRepository.save(laudoExistente);
    }
}
