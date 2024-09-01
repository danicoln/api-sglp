package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.AuthenticationInput;
import com.sglp.sglp_api.api.dto.input.RegisterInput;
import com.sglp.sglp_api.api.dto.model.LoginModel;
import com.sglp.sglp_api.core.security.TokenService;
import com.sglp.sglp_api.domain.model.user.Usuario;
import com.sglp.sglp_api.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {

    private final AuthenticationManager manager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public AuthenticationResource(AuthenticationManager manager, UsuarioRepository repository, TokenService tokenService) {
        this.manager = manager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationInput data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginModel(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterInput data) {
        if (repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
