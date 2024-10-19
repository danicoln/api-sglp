package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.PerfilInput;
import com.sglp.sglp_api.api.dto.model.PerfilModel;
import com.sglp.sglp_api.domain.model.Perfil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PerfilMapper {

    private final ModelMapper mapper;

    public Perfil toEntity(PerfilInput input) {
        return mapper.map(input, Perfil.class);
    }

    public PerfilModel toModel(Perfil obj) {
        return mapper.map(obj, PerfilModel.class);
    }

    public List<PerfilModel> toModelList(List<Perfil> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
