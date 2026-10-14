package com.devaguiar.erp.controllers;

import com.devaguiar.erp.dtos.requests.*;
import com.devaguiar.erp.dtos.responses.LoginResponseDTO;
import com.devaguiar.erp.entities.Usuario;
import com.devaguiar.erp.repositories.UsuarioRepository;
import com.devaguiar.erp.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager am, UsuarioRepository ur, TokenService ts, PasswordEncoder pe) {
        this.authenticationManager = am;
        this.usuarioRepository = ur;
        this.tokenService = ts;
        this.passwordEncoder = pe;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        Authentication auth = authenticationManager.authenticate(authToken);
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDTO data) {
        if (usuarioRepository.existsByLogin(data.login()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        String senhaCriptografada = passwordEncoder.encode(data.senha());
        usuarioRepository.save(new Usuario(data.login(), senhaCriptografada, UserRole.USER));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}