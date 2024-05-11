package com.goup.services.usuario;

import com.goup.dtos.usuario.*;
import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public UsuarioResponseDto buscarUsuarioPorId(int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            return UsuarioMapper.entityToReponse(usuarioOpt.get());
        } else {
            throw new RegistroNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public List<UsuarioResTableDto> buscarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new BuscaRetornaVazioException("Nenhum usuário encontrado!");
        }
        return UsuarioMapper.toListResTable(usuarios);
    }

    public UsuarioResponseDto criarUsuario(UsuarioCadastrarDto novoUsuario) {
        Cargo cargo = buscarCargoPorId(novoUsuario.idCargo());
        if(cargo == null){
            throw new RegistroNaoEncontradoException("Cargo não encontrado!");
        }

        Loja loja = buscarLojaPorId(novoUsuario.idLoja());
        if(loja == null){
            throw new RegistroNaoEncontradoException("Loja não encontrada!");
        }
        UsuarioBuiltDto usuarioBuiltDto = new UsuarioBuiltDto(
                novoUsuario.nome(),
                cargo,
                novoUsuario.email(),
                novoUsuario.telefone(),
                loja
        );

        return UsuarioMapper.entityToReponse(usuarioRepository.save(UsuarioMapper.toEntity(usuarioBuiltDto)));
    }

    public UsuarioResponseDto atualizarUsuario(UsuarioAtualizarDto novoUsuario, int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Cargo cargo = buscarCargoPorId(novoUsuario.idCargo());
            if(cargo == null){
                throw new RegistroNaoEncontradoException("Cargo não encontrado!");
            }

            Loja loja = buscarLojaPorId(novoUsuario.idLoja());
            if(loja == null){
                throw new RegistroNaoEncontradoException("Loja não encontrada!");
            }

            Usuario usuario = UsuarioMapper.toEntityAtualizar(novoUsuario, usuarioOpt.get(), cargo, loja);
            usuarioRepository.save(usuario);

            return UsuarioMapper.entityToReponse(usuario);
        } else {
            throw new RegistroNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public void deletarUsuario(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuarioRepository.delete(usuario);
        } else {
            throw new RegistroNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public Cargo buscarCargoPorId(int idCargo) {
        Optional<Cargo> cargoSearch = cargoRepository.findById(idCargo);
        if(cargoSearch.isPresent()){
            return cargoSearch.get();
        } else {
            return null;
        }
    }

    public Loja buscarLojaPorId(int idLoja) {
        Optional<Loja> lojaSearch = lojaRepository.findById(idLoja);
        if(lojaSearch.isPresent()){
            return lojaSearch.get();
        } else {
            return null;
        }
    }
}
