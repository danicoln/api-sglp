package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.AuthenticationInput;
import com.sglp.sglp_api.api.dto.input.RegisterInput;
import com.sglp.sglp_api.api.dto.model.LoginModel;
import com.sglp.sglp_api.api.dto.model.ResponseDto;
import com.sglp.sglp_api.core.security.TokenService;
import com.sglp.sglp_api.domain.model.user.Usuario;
import com.sglp.sglp_api.domain.repository.UsuarioRepository;
import com.sglp.sglp_api.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final UsuarioRepository repository;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationInput input) {
        Usuario user = repository.findByLogin(input.login());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (passwordEncoder.matches(input.password(), user.getPassword())) {
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginModel(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterInput input) {

        UserDetails user = repository.findByLogin(input.login());
        if (user == null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(input.password());
            Usuario newUser = new Usuario(input.nome(), input.login(), encryptedPassword, input.perfil());

            usuarioService.validateUser(newUser, newUser.getLogin());

            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getLogin(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
