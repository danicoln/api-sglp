package com.sglp.sglp_api.domain.model.user;

import com.sglp.sglp_api.domain.model.AbstractEntity;
import com.sglp.sglp_api.domain.model.Role;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "usuarios")
public class Usuario extends AbstractEntity implements UserDetails {

    @Id
    private String id;
    @Email
    private String login;
    private String password;
    private String nome;
    private Boolean ativo;
    private Role role;

    public Usuario(String nome, String login, String password, Role role) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null || role.getPermissoes() == null) {
            return Collections.emptyList();
        }
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Boolean isAtivo() {
        return ativo;
    }
}
