package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.UsuarioInput;
import com.sglp.sglp_api.domain.model.user.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeritoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInput input) {
        return modelMapper.map(input, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput input, Usuario usuario) {
        modelMapper.map(input, usuario);
    }
}
