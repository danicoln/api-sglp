package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.core.security.AuthenticatedUserService;
import com.sglp.sglp_api.domain.exception.AdvogadoExistenteException;
import com.sglp.sglp_api.domain.model.Advogado;
import com.sglp.sglp_api.domain.repository.AdvogadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvogadoService extends GenericService<Advogado, String> {

    public static final String ADVOGADO_EXISTENTE = "Já existe um advogado cadastrado com o mesmo e-mail";

    private final AdvogadoRepository repository;

    protected AdvogadoService(AdvogadoRepository repository,
                              AuthenticatedUserService usuarioAutenticadoService) {
        super(repository, usuarioAutenticadoService);
        this.repository = repository;
    }

    @Override
    @Transactional
    public Advogado inserir(Advogado advogado) {
        boolean isAdvogadoExistente = repository.existsByEmail(advogado.getEmail());
        if (isAdvogadoExistente) {
            throw new AdvogadoExistenteException(ADVOGADO_EXISTENTE);
        }
        return repository.save(advogado);
    }

    @Override
    @Transactional
    public Advogado atualizar(String advogadoId, Advogado advogado) {
        Advogado advExistente = buscarPorIdOuFalhar(advogadoId);
        BeanUtils.copyProperties(advogado, advExistente, "id");
        return repository.save(advExistente);
    }

}
