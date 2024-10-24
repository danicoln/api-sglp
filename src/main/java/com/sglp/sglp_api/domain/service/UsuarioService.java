package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.core.security.AuthenticatedUserService;
import com.sglp.sglp_api.core.security.TokenService;
import com.sglp.sglp_api.domain.exception.NegocioException;
import com.sglp.sglp_api.domain.model.user.Usuario;
import com.sglp.sglp_api.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService extends GenericService<Usuario, String> {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário com o código %s não encontrado";
    public static final String PERFIL_NAO_ENCONTRADO = "Perfil não encontrado";

    private final UsuarioRepository repository;
    private final TokenService tokenService;

    protected UsuarioService(UsuarioRepository repository,
                             AuthenticatedUserService usuarioAutenticadoService,
                             TokenService tokenService
    ) {
        super(repository, usuarioAutenticadoService);
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    public void validateSave(Usuario usuario) {
        if (usuario == null) {
            throw new NegocioException(USUARIO_NAO_ENCONTRADO);
        }
        if (usuario.getLoginInclusao() == null) {
            usuario.setLoginInclusao(usuario.getLogin());
        }
        usuario.setDataInclusao(LocalDateTime.now());
    }

    @Override
    public void validateUpdate(Usuario usuario) {

        if (usuario.getAtivo() != null) {
            usuario.setAtivo(usuario.isAtivo());
        }
    }

    public String validatePassword(Usuario usuario) {
        if (usuario != null) {
            Usuario newUser = getNovoUsuario(usuario);
            return tokenService.generateToken(newUser);
        }
        return null;
    }

    private Usuario getNovoUsuario(Usuario usuario) {
        String password = encryptPassword(usuario.getPassword());
        return new Usuario(usuario.getNome(), usuario.getLogin(),
                password, usuario.getRole());
    }

    private String encryptPassword(String password) {
        if (password != null) {
            return new BCryptPasswordEncoder().encode(password);
        }
        return null;
    }

    public Usuario buscarPorLogin(String login) {
        return repository.findByLogin(login);
    }
}
