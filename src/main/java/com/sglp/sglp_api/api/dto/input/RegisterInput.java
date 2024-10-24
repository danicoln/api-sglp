package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.Role;

public record RegisterInput(String nome, String login, String password, Role role) {
}
