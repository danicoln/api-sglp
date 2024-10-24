package com.sglp.sglp_api.core.security;

import com.sglp.sglp_api.core.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
@RequiredArgsConstructor
public class SecurityRolesAndPermissionsConfig {

    public static final String API_NOMEACOES = "/api/nomeacoes";
    public static final String API_ADVOGADOS = "/api/advogados";
    public static final String API_USUARIOS = "/api/usuarios";
    public static final String API = "/api/**";

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityRoleFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize ->
                            authorize
                                    .requestMatchers(
                                            API_NOMEACOES,
                                            API_ADVOGADOS
                                    ).hasAuthority(MANAGER.name())
                                    .requestMatchers(
                                            API
                                    ).hasAuthority(ADMIN.name())

                                    .anyRequest().authenticated()
                ).userDetailsService(userDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}