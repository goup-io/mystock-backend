package com.goup.repositories.vendas;

import com.goup.entities.vendas.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

}
