package com.goup.repositories.produtos;

import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.multiple_pk.ProdutoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, ProdutoPK> {
    List<Produto> findAllByCorAndModelo(Cor cor, Modelo modelo);
    Optional<Produto> findById(Integer id);

}
