package com.sglp.sglp_api.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissao {

    ROLE_ADMIN_CONSULTAR("admin:read"),
    ROLE_ADMIN_CADASTRAR("admin:create"),
    ROLE_ADMIN_ATUALIZAR("admin:update"),
    ROLE_ADMIN_REMOVER("admin:delete"),

    ROLE_MANAGER_CONSULTAR("manager:read"),
    ROLE_MANAGER_CADASTRAR("manager:create"),
    ROLE_MANAGER_ATUALIZAR("manager:update"),
    ROLE_MANAGER_REMOVER("manager:delete"),

    ;

    private final String permissao;
}
