package com.sglp.sglp_api.domain.model.user;

import com.sglp.sglp_api.domain.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
    private UserRole role;
    private String nome;
    private List<String> permissaoId;

    public Usuario(String nome, String login, String password, UserRole role, List<String> permissaoId) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.role = role;
        this.permissaoId = permissaoId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
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
}
