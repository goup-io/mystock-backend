package com.goup.services.produtos;

import com.goup.dtos.estoque.produtos.ProdutoMapper;
import com.goup.dtos.estoque.produtos.ProdutoReq;
import com.goup.dtos.estoque.produtos.ProdutoReqEdit;
import com.goup.dtos.estoque.produtos.ProdutoRes;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.CorRepository;
import com.goup.repositories.produtos.ModeloRepository;
import com.goup.repositories.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CorRepository corRepository;
    @Autowired
    private ModeloRepository modeloRepository;

    public ProdutoRes cadastrar(ProdutoReq produto) {
        Optional<Cor> cor = corRepository.findById(produto.idCor());
        Optional<Modelo> modelo = modeloRepository.findById(produto.idModelo());
        // COR ou MODELO não existem
        if(cor.isEmpty()){
            throw new RegistroNaoEncontradoException("Cor não encontrada!");
        }else if(modelo.isEmpty()){
            throw new RegistroNaoEncontradoException("Modelo não encontrado!");
        }
        // Conjunto é a junção de MODELO + COR
        boolean conjuntoExiste = this.repository.existsByCorAndModelo(cor.get(), modelo.get());
        if(conjuntoExiste){
            throw new RegistroConflitanteException("Produto de mesmo modelo e cor já existente!");
        }

        final Produto produtoSalvo = this.repository.save(ProdutoMapper.reqToEntity(produto, cor.get(), modelo.get()));
        return ProdutoMapper.entityToRes(produtoSalvo);
    }

    public List<ProdutoRes> listar() {
        List<Produto> produtos = this.repository.findAll();
        return ProdutoMapper.listToListRes(produtos);
    }

    public ProdutoRes buscarPorId(int id) {
        Optional<Produto> produtoOpt = repository.findById(id);
        if(produtoOpt.isPresent()){
            return ProdutoMapper.entityToRes(produtoOpt.get());
        }
        throw new RegistroNaoEncontradoException("Produto não encontrado!");
    }

    public ProdutoRes atualizar(int id, ProdutoReqEdit produtoNovo){
        Optional<Produto> produtoAtual = repository.findById(id);
        if(produtoAtual.isEmpty()){
            throw new RegistroNaoEncontradoException("Produto não encontrado");
        }
        final Produto produtoAtualizado = this.repository.save(ProdutoMapper.updateEntity(produtoAtual.get(), produtoNovo));
        return ProdutoMapper.entityToRes(produtoAtualizado);
    }

    public void deletar(int id){
        Optional<Produto> produtoOpt = repository.findById(id);
        if(produtoOpt.isPresent()){
            repository.delete(produtoOpt.get());
        } else  {
            throw new RegistroNaoEncontradoException("Produto não encontrado");
        }
    }

}
