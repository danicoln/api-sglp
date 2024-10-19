package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.PerfilInput;
import com.sglp.sglp_api.api.dto.model.PerfilModel;
import com.sglp.sglp_api.api.mapper.PerfilMapper;
import com.sglp.sglp_api.domain.exception.EntidadeNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Perfil;
import com.sglp.sglp_api.domain.service.PerfilService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/perfis")
public class PerfilResource {

    private final PerfilService service;
    private final PerfilMapper mapper;

    @GetMapping
    public List<PerfilModel> listar() {
        return mapper.toModelList(service.listar());
    }

    @PostMapping
    public ResponseEntity<PerfilModel> inserir(@RequestBody @Valid PerfilInput input) {
        Perfil perfil = mapper.toEntity(input);
        PerfilModel model = mapper.toModel(service.inserir(perfil));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{perfilId}")
    public ResponseEntity<PerfilModel> atualizar(@PathVariable String perfilId,
                                                 @RequestBody PerfilInput input) {
        Perfil perfil = mapper.toEntity(input);
        Perfil perfilAtualizado = service.atualizar(perfilId, perfil);
        PerfilModel model = mapper.toModel(perfilAtualizado);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{perfilId}")
    public ResponseEntity<PerfilModel> remover(@PathVariable String perfilId) {
        try {
            service.remover(perfilId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
