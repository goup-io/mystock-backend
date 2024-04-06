package com.goup.controllers.usuario;

import com.goup.dtos.requests.LoginDTO;
import com.goup.dtos.requests.LoginResponseDTO;
import com.goup.dtos.requests.RegisterDTO;
import com.goup.entities.usuarios.Login;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.usuarios.LoginRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.user(), loginDTO.senha());
        Authentication authenticate = this.authenticationManager.authenticate(userAuthToken);

        var usuario = (Login) authenticate.getPrincipal();
        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO){
        if (this.repository.findByUser(registerDTO.user()) != null) return ResponseEntity.status(409).build();
        String senhaEcrypted = new BCryptPasswordEncoder().encode(registerDTO.senha());

        Usuario usuario = null;

        Optional<Usuario> searchUser =  usuarioRepository.findById(registerDTO.userId());
        if (searchUser.isPresent()){
            usuario = searchUser.get();
        } else {
            return ResponseEntity.status(400).build();
        }

        Login novoLogin = new Login(registerDTO.user(), senhaEcrypted, usuario, registerDTO.role());

        this.repository.save(novoLogin);

        return ResponseEntity.status(201).build();

    }
}