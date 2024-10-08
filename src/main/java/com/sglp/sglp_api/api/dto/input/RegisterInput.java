package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.user.UserRole;

public record RegisterInput(String login, String password, UserRole role) {
}
