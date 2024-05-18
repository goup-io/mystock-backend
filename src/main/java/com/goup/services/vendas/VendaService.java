package com.goup.services.vendas;

import com.goup.dtos.vendas.venda.VendaMapper;
import com.goup.dtos.vendas.venda.VendaReq;
import com.goup.dtos.vendas.venda.VendaRes;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.vendas.TipoVendaRepository;
import com.goup.repositories.vendas.VendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoVendaRepository tipoVendaRepository;

    public List<VendaRes> listar(){
        List<Venda> vendas = repository.findAll();
        if (vendas.isEmpty()){
            throw new BuscaRetornaVazioException("Venda não encontrou algum resultado");
        }
        return VendaMapper.entityToRes(vendas);
    }

    public VendaRes buscarPorId(Integer id){
        Optional<Venda> venda = repository.findById(id);
        if (venda.isEmpty()){
            throw new  RegistroNaoEncontradoException("Venda não encontrou o ID informado");
        }
        return VendaMapper.entityToRes(venda.get());
    }

    public VendaRes salvar(@Valid VendaReq req){
        Optional<Usuario> usuario = usuarioRepository.findById(req.usuarioId());
        Optional<TipoVenda> tipoVenda = tipoVendaRepository.findById(req.tipoVendaId());
        if (usuario.isEmpty()){
            throw new RegistroNaoEncontradoException("Usuario não encontrado");
        }
        if (tipoVenda.isEmpty()){
            throw new RegistroNaoEncontradoException("TipoVenda não encontrado");
        }
        Venda venda = VendaMapper.reqToEntity(req, usuario.get(), tipoVenda.get());

        return VendaMapper.entityToRes(venda);
    }

    public VendaRes cancelarVenda(Integer id){
        Optional<Venda> vendaOpt = repository.findById(id);
        if (vendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }
        Venda venda = vendaOpt.get();
        venda.setStatusVenda(StatusVenda.CANCELADA);
        //retornar os ETPS para o estoque


        return VendaMapper.entityToRes(repository.save(venda));
    }


}
