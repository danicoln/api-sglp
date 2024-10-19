package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.core.security.AuthenticatedUserService;
import com.sglp.sglp_api.domain.exception.NegocioException;
import com.sglp.sglp_api.domain.model.Perfil;
import com.sglp.sglp_api.domain.model.user.Usuario;
import com.sglp.sglp_api.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends GenericService<Usuario, String> {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário com o código %s não encontrado";
    public static final String PERFIL_NAO_ENCONTRADO = "Perfil não encontrado";

    private final UsuarioRepository repository;
    private final PerfilService perfilService;

    protected UsuarioService(UsuarioRepository repository,
                             AuthenticatedUserService usuarioAutenticadoService,
                             PerfilService perfilService
    ) {
        super(repository, usuarioAutenticadoService);
        this.repository = repository;
        this.perfilService = perfilService;
    }

    @Override
    public void validateSave(Usuario usuario) {
        if(usuario == null) {
            throw new NegocioException(USUARIO_NAO_ENCONTRADO);
        }
    }

    @Override
    public void validateUpdate(Usuario usuario) {
        Usuario usuarioExistente = repository.findByLogin(usuario.getLogin());
        if(usuarioExistente == null) {
            throw new NegocioException(PERFIL_NAO_ENCONTRADO);
        }
        if (usuario.getPerfil() != null) {
            Optional<Perfil> perfilOpt = perfilService.buscarPorId(usuario.getPerfil().getId());
            if (perfilOpt.isEmpty()) {
                throw new NegocioException(PERFIL_NAO_ENCONTRADO);
            }

            usuario.setPerfil(perfilOpt.get());
        }
    }

    public void validateUser(Usuario user, String login) {
        if (user != null) {
            user.setLoginInclusao(login);
            user.setDataInclusao(LocalDateTime.now());
        }
        inserir(user);
    }
}
