package com.goup.repositories.produtos;

import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findAllByCorAndModelo(Cor cor, Modelo modelo);
    Optional<Produto> findById(Integer id);

    boolean existsByCorAndModelo(Cor cor, Modelo modelo);
    boolean existsByCor_IdAndModelo_Id(int idCor, int idModelo);

    boolean existsByNomeIgnoreCase(String nome);

}
