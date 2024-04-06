package com.goup.repositories.usuarios;

import com.goup.entities.cargos.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
