package com.goup.repositories.usuarios;

import com.goup.entities.cargos.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Optional<Cargo> findByNome(String nome);
}
