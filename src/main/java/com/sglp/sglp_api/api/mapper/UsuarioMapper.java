package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.UsuarioInput;
import com.sglp.sglp_api.api.dto.model.UsuarioModel;
import com.sglp.sglp_api.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final ModelMapper mapper;

    public Usuario toEntity(UsuarioInput input) {
        return mapper.map(input, Usuario.class);
    }

    public UsuarioModel toModel(Usuario obj) {
        return mapper.map(obj, UsuarioModel.class);
    }

    public List<UsuarioModel> toModelList(List<Usuario> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
