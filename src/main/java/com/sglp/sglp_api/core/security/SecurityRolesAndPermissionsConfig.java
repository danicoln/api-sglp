package com.sglp.sglp_api.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.sglp.sglp_api.domain.model.Role.ADMIN;
import static com.sglp.sglp_api.domain.model.Role.MANAGER;

@Configuration
@EnableWebSecurity
@Profile("oauth-security")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityRolesAndPermissionsConfig {

    public static final String API_NOMEACOES = "/api/nomeacoes";
    public static final String API_ADVOGADOS = "/api/advogados";
    public static final String API_USUARIOS = "/api/usuarios";
    public static final String API = "/api/**";

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityRoleFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize ->
                            authorize
                                    .requestMatchers(
                                            API_NOMEACOES,
                                            API_ADVOGADOS //... qualquer outro endpoint que n seja usuarios
                                    ).hasRole(MANAGER.name())
                                    .requestMatchers(API).hasRole(ADMIN.name())
                                    .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}