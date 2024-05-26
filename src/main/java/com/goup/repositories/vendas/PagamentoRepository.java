package com.goup.repositories.vendas;

import com.goup.entities.vendas.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
