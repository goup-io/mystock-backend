package com.goup.repositories.produtos;

import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ETPRepository extends JpaRepository<ETP, Integer>{
    Optional<ETP> findByTamanhoAndLojaAndProduto(Tamanho tamanho, Loja loja, Produto produto);

    List<ETP> findAllByLoja(Loja loja);
}
