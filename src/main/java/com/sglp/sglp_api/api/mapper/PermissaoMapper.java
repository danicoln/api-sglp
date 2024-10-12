package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.PermissaoInput;
import com.sglp.sglp_api.domain.model.Permissao;
import com.sglp.sglp_api.domain.model.PermissaoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissaoMapper {

    private final ModelMapper mapper;

    public Permissao toEntity(PermissaoInput input) {
        return mapper.map(input, Permissao.class);
    }

    public PermissaoModel toModel(Permissao obj) {
        return mapper.map(obj, PermissaoModel.class);
    }

    public List<PermissaoModel> toModelList(List<Permissao> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
