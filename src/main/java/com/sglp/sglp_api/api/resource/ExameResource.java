package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ExameInput;
import com.sglp.sglp_api.api.dto.model.ExameModel;
import com.sglp.sglp_api.api.mapper.ExameMapper;
import com.sglp.sglp_api.domain.model.Exame;
import com.sglp.sglp_api.domain.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laudos/{laudoId}/exames")
public class ExameResource {

    @Autowired
    private ExameService service;

    @Autowired
    private ExameMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExameModel>> listar(@PathVariable String laudoId) {
        var exames = service.listar(laudoId);
        return ResponseEntity.ok(mapper.toModelList(exames));
    }

    @GetMapping("/{exameId}")
    public ResponseEntity<ExameModel> buscarPoId(@PathVariable String laudoId,
                                        @PathVariable String exameId) {
        final var exame = service.buscarOuFalhar(exameId);

        if (exame != null) {
            ExameModel model = mapper.toModel(exame);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExameModel> salvar(@PathVariable String laudoId,
                                             @RequestBody ExameInput input) {
        Exame exame = mapper.toEntity(input);
        ExameModel model = mapper.toModel(service.salvar(laudoId, exame));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{exameId}")
    public ResponseEntity<ExameModel> atualizar(@PathVariable String laudoId,
                                                @PathVariable String exameId,
                                                @RequestBody ExameInput input) {
        Exame exame = mapper.toEntity(input);
        Exame exameAtualizado = service.atualizar(laudoId, exameId, exame);
        ExameModel model = mapper.toModel(exameAtualizado);

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{exameId}")
    public ResponseEntity<ExameModel> remover(@PathVariable String laudoId,
                                              @PathVariable String exameId) {
        Exame exame = service.buscarOuFalhar(exameId);
        if (exame.getId().equals(exameId)) {
            service.remover(laudoId, exameId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
