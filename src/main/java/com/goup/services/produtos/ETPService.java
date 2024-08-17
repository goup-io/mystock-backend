package com.goup.services.produtos;

import com.goup.dtos.estoque.*;
import com.goup.entities.estoque.AlertaInfos;
import com.goup.entities.estoque.AlertasEstoque;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.AlertasEstoqueRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.produtos.ProdutoRepository;
import com.goup.repositories.produtos.TamanhoRepository;
import com.goup.utils.ListaGenerica;
import com.goup.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ETPService {
    @Autowired
    private ETPRepository repository;
    @Autowired
    private TamanhoRepository tamanhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private LojaRepository lojaRepository;
    @Autowired
    private AlertasEstoqueRepository alertasEstoqueRepository;

    private Utils utils;

    public ETPTableRes cadastrar(ETPReq etp){
        Optional<Produto> produto = produtoRepository.findById(etp.idProduto());
        Optional<Loja> loja = lojaRepository.findById(etp.idLoja());
        Optional<Tamanho> tamanho = tamanhoRepository.findById(etp.idTamanho());

        if (produto.isEmpty()) {
            throw new RegistroNaoEncontradoException("Produto não encontrado!");
        }else if(loja.isEmpty()){
            throw new RegistroNaoEncontradoException("Loja não encontrada!");
        }else if(tamanho.isEmpty()){
            throw new RegistroNaoEncontradoException("Tamanho não encontrado!");
        }

        boolean etpExists = repository.findByTamanhoAndLojaAndProduto(tamanho.get(), loja.get(), produto.get()).isPresent();

        if (etpExists) {
            throw new RegistroConflitanteException("ETP de mesmo produto, tamanho e loja já existente!");
        }


        final ETP savedEtp = repository.save(ETPMapper.reqToEntity(tamanho.get(), produto.get(), loja.get(), etp.itemPromocional()));
        return ETPMapper.entityToRes(savedEtp);
    }

    public List<ETPTableRes> listar(){
        List<ETP> etps = repository.findAll();
        return ETPMapper.toTableResponse(etps);
    }

    public ETPTableRes buscarPorId(Integer id){
        Optional<ETP> etp = repository.findById(id);
        if (etp.isEmpty()) {
            throw new RegistroNaoEncontradoException("ETP não encontrado!");
        }
        return ETPMapper.toTableResponseEntity(etp.get());
    }

    public ETPEditModal buscarDtoEditPorId(Integer id){
        Optional<ETP> etp = repository.findById(id);
        if (etp.isEmpty()) {
            throw new RegistroNaoEncontradoException("ETP não encontrado!");
        }
        return ETPMapper.toEditModalEntity(etp.get());
    }
    public List<ETPTableRes> listarPorLoja(Integer id_loja) {
        Optional<Loja> loja = lojaRepository.findById(id_loja);
        if(loja.isEmpty()){
            throw new RegistroNaoEncontradoException("Loja não encontrada!");
        }
        List<ETP> etps = repository.findAllByLoja_Id(id_loja);
        ListaGenerica<ETP> etpsList = criarListaGenericaOrdenada(etps);
        return ETPMapper.toTableResponse(etpsList);
    }

    public List<ETPTableRes> listarPorFiltro(
         @Param("modelo") String modelo,
         @Param("cor") String cor,
         @Param("tamanho") Integer tamanho,
         @Param("precoMinimo") Double precoMinimo,
         @Param("precoMaximo") Double precoMaximo,
         @Param("id_loja") Integer id_loja,
         @Param("pesquisa") String pesquisa
    ) {
        List<ETP> etps = repository.findAllByFiltros(modelo, cor, tamanho, precoMinimo, precoMaximo, id_loja, pesquisa);
        return ETPMapper.toTableResponse(etps);
    }

    public ETPTableRes alterarQuantidade(Integer id, Integer quantidade, Boolean soma){
        Optional<ETP> etp = repository.findById(id);
        if (etp.isEmpty()) {
            throw new RegistroNaoEncontradoException("ETP não encontrado!");
        }

        if(soma){
            etp.get().setQuantidade(etp.get().getQuantidade() + quantidade);
        }else{
            etp.get().setQuantidade(etp.get().getQuantidade() - quantidade);
            if(etp.get().getQuantidade() <= AlertaInfos.quantidadeMinima){
                AlertasEstoque alerta = new AlertasEstoque();
                alerta.setTitulo("Alerta estoque com quantidade abaixo do ideal!");
                alerta.setDescricao("Estoque do produto " + etp.get().getProduto().getNome() + " de tamanho " + etp.get().getTamanho().getNumero() + " está em " + etp.get().getQuantidade() + "!");
                alerta.setDataHora(LocalDateTime.now());
                alerta.setEtp(etp.get());
                alertasEstoqueRepository.save(alerta);
            }
        }
        ETP savedEtp = repository.save(etp.get());
        return ETPMapper.toTableResponseEntity(savedEtp);
    }

    public List<ETPTableRes> alterarQuantidade(List<ReqETPeQuantidade> qtdPorETPId, Boolean soma, Integer idLoja){
        List<ETP> etpsSalvar = new ArrayList<>();
        for (ReqETPeQuantidade reqETPeQuantidade : qtdPorETPId) {
            Optional<ETP> etp = repository.findById(reqETPeQuantidade.idEtp());

            if (etp.isEmpty()) {
                throw new RegistroNaoEncontradoException("ETP não encontrado!");
            }

            if(soma){
                etp.get().setQuantidade(etp.get().getQuantidade() + reqETPeQuantidade.quantidade());
            }else{
                etp.get().setQuantidade(etp.get().getQuantidade() - reqETPeQuantidade.quantidade());
                if(etp.get().getQuantidade() <= AlertaInfos.quantidadeMinima){
                    AlertasEstoque alerta = new AlertasEstoque();
                    alerta.setTitulo("Alerta estoque com quantidade abaixo do ideal!");
                    alerta.setDescricao("Estoque do produto " + etp.get().getProduto().getNome() + " de tamanho " + etp.get().getTamanho().getNumero() + " está em " + etp.get().getQuantidade() + "!");
                    alerta.setDataHora(LocalDateTime.now());
                    alerta.setEtp(etp.get());
                    alertasEstoqueRepository.save(alerta);
                }
            }

            if (etp.get().getLoja().getId().equals(idLoja)){
                ETP savedEtp = repository.save(etp.get());
                etpsSalvar.add(savedEtp);
            }
        }

        return ETPMapper.toTableResponse(etpsSalvar);
    }
    public ETPTableRes atualizar(Integer id, ETPReqEdit atualizado) {
        Optional<ETP> etp = repository.findById(id);
        if(etp.isEmpty()){
            throw new RegistroNaoEncontradoException("ETP não encontrado");
        }

        Optional<Produto> produto = produtoRepository.findById(etp.get().getProduto().getId());
        if(produto.isEmpty()){
            throw new RegistroNaoEncontradoException("Produto não encontrado");
        }

        produto.get().setValorRevenda(atualizado.valorRevenda());
        produto.get().setValorCusto(atualizado.valorCusto());
        produto.get().setNome(atualizado.nome());
        produtoRepository.save(produto.get()); // Atualiza e salva produto;


        etp.get().setItemPromocional(atualizado.itemPromocional());
        repository.save(etp.get()); // Atualiza e salva ETP
        return ETPMapper.toTableResponseEntity(etp.get()); // retorna
    }
    public void deletar(Integer id){
        Optional<ETP> etp = repository.findById(id);
        if (etp.isEmpty()) {
            throw new RegistroNaoEncontradoException("ETP não encontrado!");
        }else{
            repository.deleteById(id);
        }
    }

    private ListaGenerica<ETP> criarListaGenericaOrdenada(List<ETP> etps){
        ListaGenerica<ETP> etpsList = new ListaGenerica<>(etps.size());
        for (ETP etp : etps) {
            etpsList.adiciona(etp);
        }
        utils.ordenarNome(etpsList, 0, etpsList.getTamanho());
        return etpsList;
    }


}
