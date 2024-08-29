package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.UsuarioNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Usuario;
import com.sglp.sglp_api.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário com o código %s não encontrado";

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario atualizar(String id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorIdOuFalhar(id);
        BeanUtils.copyProperties(usuario, usuarioExistente, "id");
        return usuarioRepository.save(usuarioExistente);
    }

    private Usuario buscarPorIdOuFalhar(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(USUARIO_NAO_ENCONTRADO, id));
    }
}
