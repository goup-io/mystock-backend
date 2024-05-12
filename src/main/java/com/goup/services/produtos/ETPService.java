package com.goup.services.produtos;

import com.goup.dtos.estoque.ETPEditModal;
import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.estoque.ETPReq;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import com.goup.exceptions.RegistroExistenteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.produtos.ProdutoRepository;
import com.goup.repositories.produtos.TamanhoRepository;
import com.goup.utils.ListaGenerica;
import com.goup.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
            throw new RegistroExistenteException("ETP de mesmo produto, tamanho e loja já existente!");
        }


        final ETP savedEtp = repository.save(ETPMapper.reqToEntity(tamanho.get(), produto.get(), loja.get()));
        return ETPMapper.entityToRes(savedEtp);
    }

    public List<ETPTableRes> listar(){
        List<ETP> etps = repository.findAll();
        ListaGenerica<ETP> eptsList = criarListaGenericaOrdenada(etps);
        return ETPMapper.toTableResponse(eptsList);
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
         @Param("id_loja") Integer id_loja
    ) {
        List<ETP> etps = repository.findAllByFiltros(modelo, cor, tamanho, precoMinimo, precoMaximo, id_loja);
        ListaGenerica<ETP> etpsList = criarListaGenericaOrdenada(etps);
        return ETPMapper.toTableResponse(etpsList);
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
        }
        ETP savedEtp = repository.save(etp.get());
        return ETPMapper.toTableResponseEntity(savedEtp);
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
