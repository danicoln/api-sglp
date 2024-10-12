package com.sglp.sglp_api.core.security;

import com.sglp.sglp_api.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = repository.findByLogin(username);
        return new User(user.getUsername(), user.getPassword(), getPermissoes(user));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(UserDetails user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority(
                p.getAuthority())));
        return authorities;
    }
}
