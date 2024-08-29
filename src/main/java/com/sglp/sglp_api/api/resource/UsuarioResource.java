package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.UsuarioInput;
import com.sglp.sglp_api.api.dto.model.UsuarioModel;
import com.sglp.sglp_api.api.mapper.UsuarioMapper;
import com.sglp.sglp_api.domain.model.Usuario;
import com.sglp.sglp_api.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioModel> criar(@RequestBody UsuarioInput input) {
        Usuario usuario = mapper.toEntity(input);
        UsuarioModel model = mapper.toModel(usuarioService.criar(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping
    public List<UsuarioModel> listar() {
        return mapper.toModelList(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UsuarioModel model = mapper.toModel(usuario.get());
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizar(@PathVariable String id,
                                                  @RequestBody UsuarioInput input) {
        Usuario usuario = mapper.toEntity(input);
        Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);
        UsuarioModel model = mapper.toModel(usuarioAtualizado);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable String id) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setAtivo(true);
        usuarioService.atualizar(id, usuario);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable String id) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setAtivo(false);
        usuarioService.atualizar(id, usuario);

        return ResponseEntity.noContent().build();
    }
}
