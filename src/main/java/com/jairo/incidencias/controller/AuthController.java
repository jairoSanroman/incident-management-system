package com.jairo.incidencias.controller;

import com.jairo.incidencias.entity.Usuario;
import com.jairo.incidencias.repository.UsuarioRepository;
import com.jairo.incidencias.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository,
                          JwtService jwtService,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody Usuario user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usuarioRepository.save(user);

        return "Usuario registrado correctamente";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody Usuario user) {

        Usuario usuario = usuarioRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(user.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return jwtService.generateToken(usuario.getUsername());
    }
}