package com.goup.controllers.usuario;

import com.goup.dtos.requests.LoginDTO;
import com.goup.dtos.requests.LoginResponseDTO;
import com.goup.dtos.requests.RegisterDTO;
import com.goup.dtos.requests.RegisterLoginLojaDto;
import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.AcessoLoja;
import com.goup.entities.lojas.Loja;
import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.Login;
import com.goup.entities.usuarios.UserRole;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.lojas.AcessoLojaRepository;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.LoginRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.security.InMemoryTokenBlacklist;
import com.goup.services.TokenService;

import com.goup.utils.login.VerificaTipoLogin;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private AcessoLojaRepository acessoLojaRepository;

    @Autowired
    VerificaTipoLogin verificaTipoLogin;

    @Autowired
    private InMemoryTokenBlacklist invalidateTokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){

       var tipoLogin = verificaTipoLogin.verificaTipoLogin(loginDTO.user());

        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.user(), loginDTO.senha());
        Authentication authenticate = this.authenticationManager.authenticate(userAuthToken);

        var usuario = tipoLogin instanceof Login ? (Login) authenticate.getPrincipal() :  (LojaLogin) authenticate.getPrincipal();;

        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.status(200).body(new LoginResponseDTO(token, usuario));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = invalidateTokenService.extractTokenFromRequest(request);
        invalidateTokenService.addToBlacklist(token);

        // limpar dados da sessão, caso exista

        return ResponseEntity.status(200).body(token);
    }


    @PostMapping("/register/user")
    public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO) {
        String username = registerDTO.user();
        String senha = registerDTO.senha();

        // Verificar se o usuário já existe
        if (usuarioLoginrepository.findByUser(username) != null || lojaLoginRepository.findByUser(username) != null) {
            return ResponseEntity.status(409).build(); // Conflito, usuário já existe
        }

        // Encontrar o usuário associado ao ID fornecido no DTO
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(registerDTO.userId());
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(404).build(); // Não encontrado, usuário associado ao ID não existe
        }
        Usuario usuario = optionalUsuario.get();

        // Converter o nome do cargo para um UserRole
        UserRole userRole;
        try {
            userRole = UserRole.valueOf(usuario.getCargo().getNome().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build(); // Nome do cargo inválido, não corresponde a nenhum valor no enum
        }

        // Criar um novo objeto Login com as informações fornecidas
        String senhaEncrypted = new BCryptPasswordEncoder().encode(senha);
        Login novoLogin = new Login(username, senhaEncrypted, usuario, userRole);

        // Salvar o novo login no banco de dados
        usuarioLoginrepository.save(novoLogin);

        return ResponseEntity.status(201).build(); // Criado com sucesso
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

        AcessoLoja acessoLoja = null;
        Optional<AcessoLoja> searchAcessoLoja =  acessoLojaRepository.findById(registerDTO.acessoLojaId());
        if (searchAcessoLoja.isPresent()){
            acessoLoja = searchAcessoLoja.get();
        } else {
            return ResponseEntity.status(404).build();
        }

        LojaLogin novoLogin = new LojaLogin(registerDTO.user(), senhaEcrypted, loja, acessoLoja, registerDTO.tipoLogin());

        this.lojaLoginRepository.save(novoLogin);

        return ResponseEntity.status(201).build();

    }

}
