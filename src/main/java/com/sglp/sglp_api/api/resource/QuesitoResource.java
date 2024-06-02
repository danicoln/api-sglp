package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.QuesitoInput;
import com.sglp.sglp_api.api.dto.model.QuesitoModel;
import com.sglp.sglp_api.api.mapper.QuesitoMapper;
import com.sglp.sglp_api.domain.exception.EntidadeNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.service.QuesitoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/laudos/{laudoId}/quesitos")
public class QuesitoResource {

    private final QuesitoService quesitoService;
    private final QuesitoMapper mapper;

    @GetMapping
    public ResponseEntity<List<QuesitoModel>> listar(@PathVariable String laudoId) {
        List<Quesito> quesitos = quesitoService.listar(laudoId);
        return ResponseEntity.ok(mapper.toModelList(quesitos));
    }

    @GetMapping("/{quesitoId}")
    public QuesitoModel buscar(@PathVariable String quesitoId) {
        Quesito quesito = quesitoService.buscarOuFalhar(quesitoId);

        return mapper.toModel(quesito);
    }

    @PostMapping
    public ResponseEntity<QuesitoModel> salvar(@PathVariable String laudoId,
                                               @RequestBody QuesitoInput input) {
        Quesito quesito = mapper.toEntity(input);
        QuesitoModel model = mapper.toModel(quesitoService.salvar(laudoId, quesito));

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{quesitoId}")
    public ResponseEntity<QuesitoModel> atualizar(@PathVariable String laudoId,
                                                  @PathVariable String quesitoId,
                                                  @RequestBody QuesitoInput input) {
        Quesito quesito = mapper.toEntity(input);
        Quesito quesitoAtualizado = quesitoService.atualizar(laudoId, quesitoId, quesito);
        QuesitoModel model = mapper.toModel(quesitoAtualizado);

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{quesitoId}")
    public ResponseEntity<Quesito> remover(@PathVariable String laudoId,
                                           @PathVariable String quesitoId) {
        try {
            quesitoService.remover(laudoId, quesitoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
