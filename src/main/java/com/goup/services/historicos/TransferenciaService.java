package com.goup.services.historicos;

import com.goup.dtos.historico.transferencia.TransferenciaMapper;
import com.goup.dtos.historico.transferencia.TransferenciaReq;
import com.goup.dtos.historico.transferencia.TransferenciaReqAprovar;
import com.goup.dtos.historico.transferencia.TransferenciaRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.historicos.StatusTransferencia;
import com.goup.entities.historicos.Transferencia;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.exceptions.NaoAutorizadoException;
import com.goup.exceptions.OperacaoInvalidaException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.historicos.StatusTransferenciaRepository;
import com.goup.repositories.historicos.TransferenciaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    @Autowired
    StatusTransferenciaRepository statusTransferenciaRepository;

    public TransferenciaRes cadastrar(TransferenciaReq transf){
        Optional<ETP> etp = etpRepository.findById(transf.etp_id());
        Optional<Usuario> coletor = usuarioRepository.findById(transf.coletor_id());
        Optional<StatusTransferencia> status_pendente = statusTransferenciaRepository.findByStatus(StatusTransferencia.Status.PENDENTE);
        if(etp.isEmpty()){
            throw new RegistroNaoEncontradoException("ETP não encontrado!");
        }else if(coletor.isEmpty()){
            throw new RegistroNaoEncontradoException("Usuário coletor não encontrado!");
        }else if(status_pendente.isEmpty()){
            throw new RegistroNaoEncontradoException("Status de Transferência não encontrado!");
        }else if(coletor.get().getLoja() == etp.get().getLoja()){
            throw new OperacaoInvalidaException("Usuário está solicitando item da própria loja!");
        }else if(transf.quantidadeSolicitada() < 1 || transf.quantidadeSolicitada() > etp.get().getQuantidade()){
            throw new OperacaoInvalidaException("QuantidadeSolicitada não pode ser menor que 1 ou maior que a quantidade disponível!");
        }

        Transferencia histCadastrado = repository.save(TransferenciaMapper.reqToEntity(transf, etp.get(), coletor.get(), status_pendente.get()));
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
            @Param("tamanho") Integer tamanho,
            @Param("status") StatusTransferencia.Status status
    ){
        List<Transferencia> lista = repository.findAllByFiltro(dataInicio, dataFim, modelo, cor, tamanho, status);
        List<TransferenciaRes> listaDto = TransferenciaMapper.listToListReq(lista);
        return listaDto;
    }


    public TransferenciaRes aprovar(int id, TransferenciaReqAprovar aprovacao) {
        Optional<Transferencia> transf = repository.findById(id);
        Optional<Usuario> liberador = usuarioRepository.findById(aprovacao.id_liberador());
        Optional<StatusTransferencia> status_aceite = statusTransferenciaRepository.findByStatus(StatusTransferencia.Status.ACEITO);
        validarProcessamento(transf, liberador, status_aceite); // Joga exceções se não atender os requisitos de processamento

        boolean isQtdLiberadaValida = aprovacao.quantidadeLiberada() < 1 || aprovacao.quantidadeLiberada() > transf.get().getEtp().getQuantidade();
        if(isQtdLiberadaValida){
            throw new OperacaoInvalidaException("Quantidade Liberada em casos de aprovação, não pode ser menor que 1 ou maior que a quantidade disponível!");
        }
        Transferencia transfAprovada = TransferenciaMapper.aprovar(transf.get(), aprovacao, liberador.get(), status_aceite.get());

        // Logica de transferencia
        ETP etpLiberador = transfAprovada.getEtp();

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

        Integer quantidadeTransf = transfAprovada.getQuantidadeLiberada();
        etpColetor.setQuantidade(etpColetor.getQuantidade() + quantidadeTransf);
        etpLiberador.setQuantidade(etpLiberador.getQuantidade() - quantidadeTransf);
        etpRepository.save(etpColetor);
        etpRepository.save(etpLiberador);
        repository.save(transfAprovada);

        // VERIFICAR A LOGICA AQUI, NAO FORAM REALIZADO TODOS CENARIOS...
        return TransferenciaMapper.entityToRes(transfAprovada);
    }

    //public TransferenciaRes rejeitar(int id){}

    private void validarProcessamento(Optional<Transferencia> transf, Optional<Usuario> liberador, Optional<StatusTransferencia> status_aceite){
        if(transf.isEmpty()){
            throw new RegistroNaoEncontradoException("Transferência não encontrada!");
        }else if(status_aceite.isEmpty()){
            throw new OperacaoInvalidaException("Status de Transferência inexistente!");
        }else if(transf.get().getStatus().getStatus() != StatusTransferencia.Status.PENDENTE){
            throw new OperacaoInvalidaException("Transferência já processada!");
        }else if(liberador.isEmpty()){
            throw new RegistroNaoEncontradoException("Usuário liberador não encontrado!");
        }else if(liberador.get().getLoja() != transf.get().getEtp().getLoja()){
            throw new NaoAutorizadoException("Usuário não tem permissão de liberar itens desta loja!");
        }
    }
}
