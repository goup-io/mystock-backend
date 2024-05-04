package com.goup.controllers.login;

import com.goup.dtos.login.LoginDto;
import com.goup.dtos.login.LoginResponseDTO;
import com.goup.dtos.login.redefinirSenha.RedefinirDto;
import com.goup.dtos.login.RegisterDTO;
import com.goup.dtos.login.redefinirSenha.RedefinirMapper;
import com.goup.dtos.login.redefinirSenha.RedefinirReqDto;
import com.goup.dtos.loja.loja_login.RegisterLoginLojaDto;
import com.goup.entities.lojas.AcessoLoja;
import com.goup.entities.lojas.Loja;
import com.goup.entities.lojas.LojaLogin;
import com.goup.dtos.loja.loja_login.LojaLoginResponseDTO;
import com.goup.entities.lojas.TipoLogin;
import com.goup.entities.usuarios.login.Login;
import com.goup.entities.usuarios.login.RedefinirSenha;
import com.goup.entities.usuarios.login.UserRole;
import com.goup.entities.usuarios.Usuario;
import com.goup.observer.redefinirsenha.EmailObserver;
import com.goup.repositories.lojas.AcessoLojaRepository;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import com.goup.repositories.usuarios.RedefinirSenhaRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.security.InMemoryTokenBlacklist;
import com.goup.services.TokenService;

import com.goup.services.email.EmailService;
import com.goup.utils.login.VerificaTipoLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    private AcessoLojaRepository acessoLojaRepository;

    @Autowired
    private RedefinirSenhaRepository redefinirSenhaRepository;

    @Autowired
    VerificaTipoLogin verificaTipoLogin;

    @Autowired
    private InMemoryTokenBlacklist invalidateTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailObserver emailObserver;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDTO) {
        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.user(), loginDTO.senha());
        Authentication authenticate = this.authenticationManager.authenticate(userAuthToken);

        Object tipoLogin = verificaTipoLogin.verificaTipoLogin(loginDTO.user());
        UserDetails usuario;
        Usuario userLogged;
        Loja lojaLogged;
        String token;
        if (tipoLogin instanceof Login) {
            usuario = (Login) authenticate.getPrincipal();
            token = tokenService.gerarToken(usuario);
            userLogged = ((Login) usuario).getUsuario();
            ((Login) usuario).setRole(UserRole.valueOf(userLogged.getCargo().getNome().toUpperCase()));
            return ResponseEntity.status(200).body(new LoginResponseDTO(token, userLogged.getId()));
        } else {
            usuario = (LojaLogin) authenticate.getPrincipal();
            token = tokenService.gerarToken(usuario);
            lojaLogged = ((LojaLogin) usuario).getLoja();
            ((LojaLogin) usuario).setRole(((LojaLogin) usuario).getAcessoLoja().getTipo());
            return ResponseEntity.status(200).body(new LojaLoginResponseDTO(token, lojaLogged.getId(), ((LojaLogin) usuario).getRole()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String token = invalidateTokenService.extractTokenFromRequest(request);
        invalidateTokenService.addToBlacklist(token);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/register/user")
    public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO) {
        String username = registerDTO.user();
        String senha = registerDTO.senha();

        if (usuarioLoginrepository.findByUsername(username) != null || lojaLoginRepository.findByUsername(username) != null) {
            return ResponseEntity.status(409).build();
        }

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(registerDTO.userId());
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = optionalUsuario.get();

        UserRole userRole = null;
        try {
            userRole = UserRole.valueOf(usuario.getCargo().getNome().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }

        // Criar um novo objeto Login com as informações fornecidas
        String senhaEncrypted = new BCryptPasswordEncoder().encode(senha);
        Login novoLogin = new Login(username, senhaEncrypted, usuario, userRole);

        // Salvar o novo login no banco de dados
        usuarioLoginrepository.save(novoLogin);

        return ResponseEntity.status(201).build(); // Criado com sucesso
    }

    @PostMapping("/register/loja")
    public ResponseEntity registerLoja(@RequestBody RegisterLoginLojaDto registerDTO) {
        if (this.lojaLoginRepository.findByUsername(registerDTO.user()) != null || this.usuarioLoginrepository.findByUsername(registerDTO.user()) != null)
            return ResponseEntity.status(409).build();
        String senhaEcrypted = new BCryptPasswordEncoder().encode(registerDTO.senha());

        Loja loja = null;

        Optional<Loja> searchUser = lojaRepository.findById(registerDTO.idLoja());
        if (searchUser.isPresent()) {
            loja = searchUser.get();
        } else {
            return ResponseEntity.status(404).build();
        }

        AcessoLoja acessoLoja;
        Optional<AcessoLoja> searchAcessoLoja = acessoLojaRepository.findById(registerDTO.idAcessoLoja());
        if (searchAcessoLoja.isPresent()) {
            acessoLoja = searchAcessoLoja.get();
        } else {
            return ResponseEntity.status(404).build();
        }

        TipoLogin tipoLogin = acessoLoja.getTipo();

        LojaLogin novoLogin = new LojaLogin(registerDTO.user(), senhaEcrypted, loja, acessoLoja, tipoLogin);

        this.lojaLoginRepository.save(novoLogin);

        return ResponseEntity.status(201).build();

    }

    @PostMapping("/redefinir-senha/enviar-email/{email}")
    public ResponseEntity<String> redefinirSenhaEnviarEmail(@PathVariable String email) {
        Login loginEncontrado = usuarioLoginrepository.findLoginByEmail(email);
        if (loginEncontrado == null) {
            return ResponseEntity.status(404).build();
        }
        String token = UUID.randomUUID().toString();
        RedefinirSenha emailGerado = redefinirSenhaRepository.save(RedefinirMapper.redefinirReqToEntity(new RedefinirReqDto(loginEncontrado, true, token)));

        emailObserver.enviar(email, loginEncontrado.getUsuario().getNome(), emailGerado.getToken());

        return ResponseEntity.status(201).body("E-mail enviado com sucesso!");
    }

    @PostMapping("/redefinir-senha/alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody RedefinirDto redefinirDto, @RequestParam String token) {
        // verificando se o se token é valido (se ele existe e se já não foi utilizado)
        Optional<RedefinirSenha> tokenEncontrado = redefinirSenhaRepository.findByToken(token);
        if (tokenEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else if (!tokenEncontrado.get().isAtivo()) {
            return ResponseEntity.status(401).build();
        }

        // Verificando se o token é de um login de loja ou de um login de usuario
        Object tipoLogin = verificaTipoLogin.verificaTipoLogin(tokenEncontrado.get().getLogin().getUsername());
        RedefinirSenha redefinirSenha = tokenEncontrado.get();
        if (tipoLogin instanceof Login) {
            Login login = usuarioLoginrepository.findById(tokenEncontrado.get().getLogin().getId()).get();
            login.setSenha(new BCryptPasswordEncoder().encode(redefinirDto.senha()));
            usuarioLoginrepository.save(login);
            redefinirSenha.setAtivo(false);
            redefinirSenhaRepository.save(redefinirSenha);
        } else {
            LojaLogin login = lojaLoginRepository.findById(tokenEncontrado.get().getLogin().getId()).get();
            login.setSenha(new BCryptPasswordEncoder().encode(redefinirDto.senha()));
            lojaLoginRepository.save(login);
            redefinirSenha.setAtivo(false);
            redefinirSenhaRepository.save(redefinirSenha);
        }
        return ResponseEntity.status(200).build();
    }
}