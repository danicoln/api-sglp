package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.ExameInput;
import com.sglp.sglp_api.api.dto.model.ExameModel;
import com.sglp.sglp_api.domain.model.Exame;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExameMapper {

    private final ModelMapper mapper;

    public Exame toEntity(ExameInput input) {
        return mapper.map(input, Exame.class);
    }

    public ExameModel toModel(Exame obj) {
        return mapper.map(obj, ExameModel.class);
    }

    public List<ExameModel> toModelList(List<Exame> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }

}
