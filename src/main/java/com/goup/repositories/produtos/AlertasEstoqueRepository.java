package com.goup.repositories.produtos;

import com.goup.entities.estoque.AlertasEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertasEstoqueRepository extends JpaRepository<AlertasEstoque, Integer> {
    List<AlertasEstoque> findAllByEtpLoja_Id(int id_loja);
}
