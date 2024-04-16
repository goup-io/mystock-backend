package com.goup.repositories.wip;

import com.goup.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    List<Estoque> findAll();

    List<Estoque> findAllByLojaId(Integer lojaId);
}