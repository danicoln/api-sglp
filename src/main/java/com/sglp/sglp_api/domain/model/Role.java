package com.sglp.sglp_api.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Permissao.ROLE_ADMIN_CADASTRAR,
                    Permissao.ROLE_ADMIN_CONSULTAR,
                    Permissao.ROLE_ADMIN_ATUALIZAR,
                    Permissao.ROLE_ADMIN_REMOVER,

                    Permissao.ROLE_MANAGER_CADASTRAR,
                    Permissao.ROLE_MANAGER_CONSULTAR,
                    Permissao.ROLE_MANAGER_ATUALIZAR,
                    Permissao.ROLE_MANAGER_REMOVER
            )
    ),
    MANAGER(
            Set.of(
                    Permissao.ROLE_MANAGER_CADASTRAR,
                    Permissao.ROLE_MANAGER_CONSULTAR,
                    Permissao.ROLE_MANAGER_ATUALIZAR,
                    Permissao.ROLE_MANAGER_REMOVER
            )
    )
    ;

    private final Set<Permissao> permissoes;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissoes()
                .stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getPermissao()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
