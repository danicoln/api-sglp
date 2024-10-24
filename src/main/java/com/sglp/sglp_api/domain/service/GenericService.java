package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.core.security.AuthenticatedUserService;
import com.sglp.sglp_api.domain.model.AbstractEntity;
import com.sglp.sglp_api.domain.repository.GenericRepository;
import com.sglp.sglp_api.utils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericService<T extends AbstractEntity, ID> {

    public static final String ENTIDADE_NAO_ENCONTRADA = "Entidade com id %s não encontrada";

    private final GenericRepository<T, ID> repository;
    private final AuthenticatedUserService usuarioAutenticadoService;

    protected GenericService(GenericRepository<T, ID> repository, AuthenticatedUserService usuarioAutenticadoService) {
        this.repository = repository;
        this.usuarioAutenticadoService = usuarioAutenticadoService;
    }

    @Transactional
    public T inserir(T entity) {
        validateSave(entity);
        return repository.save(entity);
    }

    public void validateSave(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entidade nula, tente novamente");
        }
        entity.setDataInclusao(LocalDateTime.now());
    }

    public void validateUpdate(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entidade nula, tente novamente");
        }
    }

    @Transactional
    public T atualizar(ID id, T entity) {
        String login = usuarioAutenticadoService.getAuthenticatedUserLogin();
        T existingEntity = buscarPorIdOuFalhar(id);
        PropertyUtils.copyNonNullProperties(entity,existingEntity,"id", "ativo", "loginInclusao", "dataInclusao");
        validateUpdate(existingEntity);
        existingEntity.setLoginAtualizacao(login);
        existingEntity.setDataAtualizacao(LocalDateTime.now());
        return repository.save(existingEntity);
    }

    @Transactional
    public void remover(ID id) {
        T entity = buscarPorIdOuFalhar(id);
        repository.delete(entity);
    }

    public List<T> listar() {
        return repository.findAll();
    }

    public Optional<T> buscarPorId(ID id) {
        return repository.findById(id);
    }

    public T buscarPorIdOuFalhar(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(ENTIDADE_NAO_ENCONTRADA, id)));
    }

}
