package com.goup.repositories.produtos;

import com.goup.entities.estoque.produtos.modelos.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    @Query("SELECT m FROM Modelo m join Produto p on m.id = p.modelo.id join ETP e on p.id = e.produto.id where e.loja.id = :loja_id")
    List<Modelo> findAllByLoja(@Param("loja_id") Integer loja_id);

    boolean existsById(int id);

    @Query("SELECT m FROM Modelo m " +
            "WHERE (:categoria IS NULL or lower(m.categoria.nome) LIKE lower(concat('%', :categoria, '%'))) " +
            "AND (:tipo IS NULL OR lower(m.tipo.nome) LIKE lower(concat('%', :tipo, '%')))" +
            "AND (:modelo IS NULL OR lower(m.nome) LIKE lower(concat('%', :modelo, '%')))")
    List<Modelo> findAllByFiltro(
            @Param("categoria") String categoria,
            @Param("tipo") String tipo,
            @Param("modelo") String modelo
    );
}
