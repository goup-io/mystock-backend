package com.goup.services.historicos;

import com.goup.dtos.historico.TransferenciaMapper;
import com.goup.dtos.historico.TransferenciaReq;
import com.goup.dtos.historico.TransferenciaReqAprovar;
import com.goup.dtos.historico.TransferenciaRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.historicos.Transferencia;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.exceptions.NaoAutorizadoException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.exceptions.transferencia.QuantidadeTransferenciaInvalidaException;
import com.goup.exceptions.transferencia.SolicitacaoProprioEtpException;
import com.goup.repositories.historicos.TransferenciaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaRepository repository;
    @Autowired
    ETPRepository etpRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public TransferenciaRes cadastrar(TransferenciaReq transf){
        Optional<ETP> etp = etpRepository.findById(transf.etp_id());
        Optional<Usuario> coletor = usuarioRepository.findById(transf.coletor_id());
        if(etp.isEmpty()){
            throw new RegistroNaoEncontradoException("ETP não encontrado!");
        }else if(coletor.isEmpty()){
            throw new RegistroNaoEncontradoException("Usuário coletor não encontrado!");
        }else if(coletor.get().getLoja() == etp.get().getLoja()){
            throw new SolicitacaoProprioEtpException("Usuário está solicitando item da própria loja!");
        }else if(transf.quantidadeSolicitada() < 0 || transf.quantidadeSolicitada() > etp.get().getQuantidade()){
            throw new QuantidadeTransferenciaInvalidaException("QuantidadeSolicitada não pode ser menor que 0 ou maior que a quantidade disponível!");
        }

        Transferencia histCadastrado = repository.save(TransferenciaMapper.reqToEntity(transf, etp.get(), coletor.get()));
        return TransferenciaMapper.entityToRes(histCadastrado);
    }

    public List<TransferenciaRes> listar(){
        List<Transferencia> lista = repository.findAll();
        List<TransferenciaRes> listaDto = TransferenciaMapper.listToListReq(lista);
        return listaDto;
    }

    public List<TransferenciaRes> listarPorFiltro(
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim,
        @Param("modelo") String modelo,
        @Param("cor") String cor,
        @Param("tamanho") Integer tamanho
    ){
        List<Transferencia> lista = repository.findAllByFiltro(dataInicio, dataFim, modelo, cor, tamanho);
        List<TransferenciaRes> listaDto = TransferenciaMapper.listToListReq(lista);
        return listaDto;
    }


    public TransferenciaRes aprovar(int id, TransferenciaReqAprovar aprovacao) {
        Optional<Transferencia> transf = repository.findById(id);
        Optional<Usuario> liberador = usuarioRepository.findById(aprovacao.id_liberador());
        if(transf.isEmpty()){
            throw new RegistroNaoEncontradoException("Transferência não encontrada!");
        }else if(liberador.isEmpty()){
            throw new RegistroNaoEncontradoException("Usuário liberador não encontrado!");
        }else if(liberador.get().getLoja() != transf.get().getEtp().getLoja()){
            throw new NaoAutorizadoException("Usuário não tem permissão de liberar itens desta loja!");
        }else if(aprovacao.quantidadeLiberada() < 0 || aprovacao.quantidadeLiberada() > transf.get().getEtp().getQuantidade()){
            throw new QuantidadeTransferenciaInvalidaException("Quantidade Liberada não pode ser menor que 0 ou maior que a quantidade disponível!");
        }
        Transferencia transfAprovada = TransferenciaMapper.aprovar(transf.get(), aprovacao, liberador.get());

        // Logica de transferencia
        ETP etpLiberador = transfAprovada.getEtp();

        Integer quantidadeTransf = transfAprovada.getQuantidadeLiberada();

        Tamanho tamanho = etpLiberador.getTamanho();
        Produto produto = etpLiberador.getProduto();
        Loja lojaColetor = transfAprovada.getColetor().getLoja();

        Optional<ETP> etpColetorOptional = etpRepository.findByTamanhoAndLojaAndProduto(tamanho, lojaColetor, produto);
        ETP etpColetor = etpColetorOptional.isPresent() ? etpColetorOptional.get() : null ;
        // Verifica se o Coletor já possui esse ETP registrado
        if(etpColetorOptional.isEmpty()){
            // Lógica de criação do ETP;
            ETP etpNovo = new ETP();
            etpNovo.setQuantidade(0);
            etpNovo.setLoja(lojaColetor);
            etpNovo.setProduto(produto);
            etpNovo.setTamanho(tamanho);
            etpColetor = etpRepository.save(etpNovo);
        }
        etpColetor.setQuantidade(etpColetor.getQuantidade() + quantidadeTransf);
        etpLiberador.setQuantidade(etpLiberador.getQuantidade() - quantidadeTransf);
        etpRepository.save(etpColetor);
        repository.save(transfAprovada);
        return TransferenciaMapper.entityToRes(transfAprovada);
    }
}
