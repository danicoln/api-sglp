package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.core.security.AuthenticatedUserService;
import com.sglp.sglp_api.domain.exception.NegocioException;
import com.sglp.sglp_api.domain.model.Perfil;
import com.sglp.sglp_api.domain.model.Permissao;
import com.sglp.sglp_api.domain.repository.PerfilRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService extends GenericService<Perfil, String> {

    public static final String PERFIL_NAO_ENCONTRADO = "Perfil não encontrado";
    public static final String PERFIL_COM_MESMO_NOME_EXISTENTE = "Perfil com mesmo nome existente";

    private final PerfilRepository repository;
    private final PermissaoService permissaoService;

    protected PerfilService(PerfilRepository repository,
                            AuthenticatedUserService usuarioAutenticadoService,
                            PermissaoService permissaoService) {
        super(repository, usuarioAutenticadoService);
        this.repository = repository;
        this.permissaoService = permissaoService;
    }

    public List<Perfil> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Perfil inserir(Perfil perfil) {
        Optional<Perfil> perfilComMesmoNome = repository.findByNome(perfil.getNome());
        if (perfilComMesmoNome.isPresent()) {
            throw new NegocioException(PERFIL_COM_MESMO_NOME_EXISTENTE);
        }
        validateSave(perfil);
        return repository.save(perfil);
    }

    @Override
    public void validateSave(Perfil perfil) {
        Optional<Perfil> perfilComMesmoNome = repository.findByNome(perfil.getNome());
        if (perfilComMesmoNome.isPresent()) {
            throw new NegocioException(PERFIL_COM_MESMO_NOME_EXISTENTE);
        }

        validarPermissoes(perfil);
    }

    @Transactional
    public Perfil atualizar(String perfilId, Perfil perfil) {
        Perfil perfilExistente = buscarPorIdOuFalhar(perfilId);
        BeanUtils.copyProperties(perfil, perfilExistente, "id");
        validateUpdate(perfilExistente);
        return repository.save(perfilExistente);

    }

    @Override
    public void validateUpdate(Perfil perfil) {
        Optional<Perfil> perfilComMesmoNome = repository.findByNome(perfil.getNome());
        if (perfilComMesmoNome.isPresent() && !perfilComMesmoNome.get().getId().equals(perfil.getId())) {
            throw new NegocioException(PERFIL_COM_MESMO_NOME_EXISTENTE);
        }
        validarPermissoes(perfil);
    }

    @Transactional
    public void remover(String perfilId) {
        repository.deleteById(perfilId);
    }

    @Override
    public Perfil buscarPorIdOuFalhar(String perfilId) {
        return repository.findById(perfilId).orElseThrow(() -> new RuntimeException(PERFIL_NAO_ENCONTRADO));
    }


    public Perfil adicionarPermissao(String perfilId, String permissaoId) {
        Optional<Perfil> perfilOpt = repository.findById(perfilId);
        Optional<Permissao> permissaoOpt = permissaoService.buscarPorId(permissaoId);

        if (perfilOpt.isPresent() && permissaoOpt.isPresent()) {
            Perfil perfil = perfilOpt.get();
            Permissao permissao = permissaoOpt.get();

            if (!perfil.getPermissoes().contains(permissao)) {
                perfil.getPermissoes().add(permissao);
                return repository.save(perfil);
            } else {
                throw new NegocioException("Permissão já associada ao perfil");
            }
        }
        throw new NegocioException("Perfil ou permissão não encontrados");
    }

    public Perfil removerPermissao(String perfilId, String permissaoId) {
        Optional<Perfil> perfilOpt = repository.findById(perfilId);

        if (perfilOpt.isPresent()) {
            Perfil perfil = perfilOpt.get();
            perfil.getPermissoes().removeIf(permissao -> permissao.getId().equals(permissaoId));

            return repository.save(perfil);
        }
        throw new NegocioException("Perfil não encontrado.");
    }

    private void validarPermissoes(Perfil perfil) {
        if (perfil.getPermissoes() == null || perfil.getPermissoes().isEmpty()) {
            throw new NegocioException("O perfil deve ter pelo menos uma permissão.");
        }

        for (Permissao permissao : perfil.getPermissoes()) {
            Optional<Permissao> permissaoExistente = permissaoService.buscarPorId(permissao.getId());
            if (permissaoExistente.isEmpty()) {
                throw new NegocioException("Permissão inválida: " + permissao.getDescricao());
            }
        }
    }
}
