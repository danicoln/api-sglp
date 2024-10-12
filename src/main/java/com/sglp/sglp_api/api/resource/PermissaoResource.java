package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.PermissaoInput;
import com.sglp.sglp_api.api.mapper.PermissaoMapper;
import com.sglp.sglp_api.domain.exception.AdvogadoNaoEncontradoException;
import com.sglp.sglp_api.domain.exception.EntidadeNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Permissao;
import com.sglp.sglp_api.domain.model.PermissaoModel;
import com.sglp.sglp_api.domain.service.IAService;
import com.sglp.sglp_api.domain.service.PermissaoService;
import com.sglp.sglp_api.domain.service.strategy.LaudoStrategy;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/permissoes")
public class PermissaoResource {

    private final PermissaoService permissaoService;
    private final PermissaoMapper mapper;


    @GetMapping
    public List<PermissaoModel> listar() {
        return mapper.toModelList(permissaoService.listar());
    }

    @PostMapping
    public ResponseEntity<PermissaoModel> inserir(@RequestBody @Valid PermissaoInput input) {
        Permissao permissao = mapper.toEntity(input);
        PermissaoModel model = mapper.toModel(permissaoService.salvar(permissao));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<PermissaoModel> atualizar(@PathVariable String permissaoId,
                                                    @RequestBody PermissaoInput input) {
        Permissao permissao = mapper.toEntity(input);
        Permissao permissaoAtualizado = permissaoService.atualizar(permissaoId, permissao);
        PermissaoModel model = mapper.toModel(permissaoAtualizado);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<PermissaoModel> remover(@PathVariable String permissaoId) {
        try {
            permissaoService.remover(permissaoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
