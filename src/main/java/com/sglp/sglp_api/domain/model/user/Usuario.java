package com.sglp.sglp_api.domain.model.user;

import com.sglp.sglp_api.domain.model.AbstractEntity;
import com.sglp.sglp_api.domain.model.Perfil;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
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
    private Perfil perfil;

    public Usuario(String nome, String login, String password, Perfil perfil) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (perfil == null || perfil.getPermissoes() == null) {
            return Collections.emptyList();
        }
        return perfil.getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getDescricao()))
                .collect(Collectors.toSet());
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
