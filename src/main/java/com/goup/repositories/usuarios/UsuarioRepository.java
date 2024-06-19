package com.goup.repositories.usuarios;

import com.goup.dtos.dashboards.dashboardGeral.RankingFuncionariosRes;
import com.goup.dtos.relatorios.RankingFuncionariosVendas;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u JOIN u.cargo c")
    List<Usuario> findAllWithJoin();

    List<Usuario> findAllByLoja(Loja loja);
    List<Usuario> findAllByLoja_Id(int id_loja);


    @Query("SELECT MAX(u.id) FROM Usuario u")
    Integer findMaxId();

    Optional<Usuario> findByCodigoVenda(Integer codigoVenda);

    @Query("SELECT new com.goup.dtos.dashboards.dashboardGeral.RankingFuncionariosRes(u.id, u.nome, SUM(v.valorTotal)) FROM Venda v " +
            "JOIN v.usuario u " +
            "WHERE u.loja.id = :idLoja " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND MONTH(v.dataHora) = :mes " +
            "AND YEAR(v.dataHora) = :ano  " +
            "GROUP BY u.id " +
            "ORDER BY SUM(v.valorTotal) DESC")
    List<RankingFuncionariosRes> sumValorVendidoByUsuario(@Param("idLoja") Integer idLoja, @Param("mes") Integer mes, @Param("ano") Integer ano);

    @Query("SELECT new com.goup.dtos.relatorios.RankingFuncionariosVendas(" +
            "u.cargo.descricao, u.nome, COUNT(v.id), SUM(v.valorTotal), " +
            "(SELECT SUM(pv.quantidade) FROM ProdutoVenda pv WHERE v.usuario.id = u.id AND pv.etp.itemPromocional = 'SIM')" +
            ") FROM Venda v " +
            "JOIN v.usuario u " +
            "WHERE v.statusVenda.status = 'FINALIZADA'" +
            "AND v.dataHora >= :dataInicial " +
            "GROUP BY u.id " +
            "ORDER BY SUM(v.valorTotal) DESC")
    List<RankingFuncionariosVendas> sumValorVendidoByUsuarioPeriod(@Param("dataInicial")LocalDateTime dataInicial);

}