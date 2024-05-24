package com.goup.services.vendas;

import com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade;
import com.goup.dtos.vendas.venda.VendaMapper;
import com.goup.dtos.vendas.venda.VendaReq;
import com.goup.dtos.vendas.venda.VendaRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import com.goup.repositories.vendas.StatusVendaRepository;
import com.goup.repositories.vendas.TipoVendaRepository;
import com.goup.repositories.vendas.VendaRepository;
import com.goup.services.produtos.ETPService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private StatusVendaRepository statusVendaRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

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

        Optional<StatusVenda> statusEmAndamento = statusVendaRepository.findByStatus(StatusVenda.Status.PENDENTE);
        if (statusEmAndamento.isEmpty()){
            throw new RegistroNaoEncontradoException("StatusVenda não encontrado");
        }
        venda.setStatusVenda(statusEmAndamento.get());

        return VendaMapper.entityToRes(venda);
    }

    //todo: dar baixa nos produtos - feito; adicionar no histórico
    public VendaRes finalizarVenda(Integer idVenda){
        Optional<Venda> vendaOpt = repository.findById(idVenda);
        if (vendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }

        // mudando o status da venda para finalizada
        Optional<StatusVenda> statusFinalizada = statusVendaRepository.findByStatus(StatusVenda.Status.FINALIZADA);
        if (statusFinalizada.isEmpty()){
            throw new RegistroNaoEncontradoException("StatusVenda não encontrado");
        }
        Venda venda = vendaOpt.get();
        venda.setStatusVenda(statusFinalizada.get());
        repository.save(venda);

        // dando baixa dos produtos no estoque
        alterarEtpBaseadoVenda(venda.getId(), false);

        return VendaMapper.entityToRes(venda);
    }

    public VendaRes cancelarVenda(Integer id){
        Optional<Venda> vendaOpt = repository.findById(id);
        if (vendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }
        Venda venda = vendaOpt.get();

        if (venda.getStatusVenda().getStatus().getDescricao().equals("Cancelada")){
            throw new RegistroConflitanteException("Venda já está cancelada");
        }

        Optional<StatusVenda> statusCancelado = statusVendaRepository.findByStatus(StatusVenda.Status.CANCELADA);
        if (statusCancelado.isEmpty()){
            throw new RegistroNaoEncontradoException("StatusVenda não encontrado");
        }
        venda.setStatusVenda(statusCancelado.get());
        Venda vendaSalvada = repository.save(venda);

        alterarEtpBaseadoVenda(venda.getId(), true);

        return VendaMapper.entityToRes(vendaSalvada);
    }

    public void alterarEtpBaseadoVenda(Integer idVenda, Boolean soma){
        List<RetornoETPeQuantidade> etps = produtoVendaRepository.findAllEtpsByVendaId(idVenda);
        List<ETP> etpsSalvos = new ArrayList<>();
        for (RetornoETPeQuantidade etp : etps) {
            ETP etpAtualizar = etp.etp();
            if (soma){
                etpAtualizar.setQuantidade(etpAtualizar.getQuantidade() + etp.quantidade());
            } else {
                etpAtualizar.setQuantidade(etpAtualizar.getQuantidade() - etp.quantidade());
            }
            etpsSalvos.add(etpAtualizar);
        }
        // salvando a atualização de quantidade dos ETPS
        etpRepository.saveAll(etpsSalvos);
    }

}
