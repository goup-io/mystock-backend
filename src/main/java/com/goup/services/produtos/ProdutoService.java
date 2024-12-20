package com.goup.services.produtos;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.estoque.ETPReq;
import com.goup.dtos.estoque.produtos.ProdutoMapper;
import com.goup.dtos.estoque.produtos.ProdutoReq;
import com.goup.dtos.estoque.produtos.ProdutoRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.entities.lojas.Loja;
import com.goup.entities.vendas.ItemPromocional;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.*;
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
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private LojaRepository lojaRepository;
    @Autowired
    private TamanhoRepository tamanhoRepository;

    public ProdutoRes cadastrar(ProdutoReq produto) {
        Optional<Cor> cor = corRepository.findById(produto.idCor());
        Optional<Modelo> modelo = modeloRepository.findById(produto.idModelo());
        Optional<Loja> loja = lojaRepository.findById(produto.idLoja());
        Optional<Tamanho> tamanho = tamanhoRepository.findByNumero(produto.tamanho());
        // COR ou MODELO não existem
        if(cor.isEmpty()){
            throw new RegistroNaoEncontradoException("Cor não encontrada!");
        }else if(modelo.isEmpty()){
            throw new RegistroNaoEncontradoException("Modelo não encontrado!");
        } else if (loja.isEmpty()){
            throw new RegistroNaoEncontradoException("Loja não encontrada!");
        } else if (tamanho.isEmpty()){
            throw new RegistroNaoEncontradoException("Tamanho não encontrado!");
        }

        boolean isCodigoDuplicado = etpRepository.existsByCodigoAndLoja_Id(produto.codigo(), produto.idLoja());
        if(isCodigoDuplicado){
            throw new RegistroConflitanteException("Código de produto já existente!");
        }

        // Conjunto é a junção de MODELO + COR
        boolean conjuntoExiste = this.repository.existsByCorAndModelo(cor.get(), modelo.get());
        boolean codigoExiste = etpRepository.existsByCodigoAndLoja_Id(produto.codigo(), produto.idLoja());

        if(codigoExiste) throw new RegistroConflitanteException("Código já usado em outro produto");

        if(conjuntoExiste){
            List<Produto> produtos = repository.findAllByCorAndModelo(cor.get(), modelo.get());
            Produto produtoEncontrado = produtos.get(0);
            Optional<ETP> etpSearch = etpRepository.findByTamanhoAndLojaAndProduto(tamanho.get(), loja.get(), produtoEncontrado);

            if (etpSearch.isPresent()){
                throw new RegistroConflitanteException("Produto de mesmo modelo, cor e tamanho já existente!");
            }

            etpRepository.save(ETPMapper.reqToEntity(produto.codigo(), tamanho.get(), produtoEncontrado, loja.get(), produto.itemPromocional(), produto.quantidade()));
        }


        final Produto produtoSalvo = this.repository.save(ProdutoMapper.reqToEntity(produto, cor.get(), modelo.get()));
        etpRepository.save(ETPMapper.reqToEntity(produto.codigo(), tamanho.get(), produtoSalvo, loja.get(), produto.itemPromocional(), produto.quantidade()));
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

    public void deletar(int id){
        Optional<Produto> produtoOpt = repository.findById(id);
        if(produtoOpt.isPresent()){
            repository.delete(produtoOpt.get());
        } else  {
            throw new RegistroNaoEncontradoException("Produto não encontrado");
        }
    }

}
