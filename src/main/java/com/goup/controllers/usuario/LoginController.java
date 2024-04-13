package com.goup.controllers.usuario;

import com.goup.dtos.requests.LoginDTO;
import com.goup.dtos.requests.LoginResponseDTO;
import com.goup.dtos.requests.RegisterDTO;
import com.goup.dtos.requests.RegisterLoginLojaDto;
import com.goup.entities.lojas.Loja;
import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.Login;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.lojas.LojaRepository;
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
    private LoginRepository usuarioLoginrepository;

    @Autowired
    private LoginLojaRepository lojaLoginRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LojaRepository lojaRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){

       var tipoLogin = verificaTipoLogin(loginDTO);

        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.user(), loginDTO.senha());
        Authentication authenticate = this.authenticationManager.authenticate(userAuthToken);

        var usuario = tipoLogin instanceof Login ? (Login) authenticate.getPrincipal() :  (LojaLogin) authenticate.getPrincipal();;

        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.status(200).body(new LoginResponseDTO(token, usuario));
    }

    /*
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = tokenService.extractTokenFromRequest(request);
        invalidateTokenService.addToBlacklist(token);

        // limpar dados da sessão, caso exista

        return ResponseEntity.status(200).body(token);
    }
     */

    @PostMapping("/register/user")
    public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO){
        if (this.usuarioLoginrepository.findByUser(registerDTO.user()) != null || this.lojaLoginRepository.findByUser(registerDTO.user()) != null) return ResponseEntity.status(409).build();
        String senhaEcrypted = new BCryptPasswordEncoder().encode(registerDTO.senha());

        Usuario usuario = null;

        Optional<Usuario> searchUser =  usuarioRepository.findById(registerDTO.userId());
        if (searchUser.isPresent()){
            usuario = searchUser.get();
        } else {
            return ResponseEntity.status(404).build();
        }

        Login novoLogin = new Login(registerDTO.user(), senhaEcrypted, usuario, registerDTO.role());

        this.usuarioLoginrepository.save(novoLogin);

        return ResponseEntity.status(201).build();

    }

    @PostMapping("/register/loja")
    public ResponseEntity registerLoja(@RequestBody RegisterLoginLojaDto registerDTO){
        if (this.lojaLoginRepository.findByUser(registerDTO.user()) != null || this.usuarioLoginrepository.findByUser(registerDTO.user()) != null) return ResponseEntity.status(409).build();
        String senhaEcrypted = new BCryptPasswordEncoder().encode(registerDTO.senha());

        Loja loja = null;

        Optional<Loja> searchUser =  lojaRepository.findById(registerDTO.userId());
        if (searchUser.isPresent()){
            loja = searchUser.get();
        } else {
            return ResponseEntity.status(404).build();
        }

        LojaLogin novoLogin = new LojaLogin(registerDTO.user(), senhaEcrypted, loja, registerDTO.tipoLogin());

        this.lojaLoginRepository.save(novoLogin);

        return ResponseEntity.status(201).build();

    }

    public Object verificaTipoLogin(LoginDTO loginDTO) {
        boolean isLoginUser = usuarioLoginrepository.findByUser(loginDTO.user()) != null;

        boolean isLoginLoja = lojaLoginRepository.findByUser(loginDTO.user()) != null;

        if (isLoginLoja){
            return new LojaLogin();
        }
        if (isLoginUser){
            return new Login();
        }
        return null;
    }

}
