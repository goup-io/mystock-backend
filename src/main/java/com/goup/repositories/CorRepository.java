package com.goup.repositories;

import com.goup.entities.produtos.Cor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorRepository extends JpaRepository<Cor, Integer> {
}
