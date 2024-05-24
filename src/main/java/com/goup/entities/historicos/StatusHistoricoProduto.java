package com.goup.entities.historicos;

import com.goup.entities.vendas.StatusVenda;
import com.goup.exceptions.RegistroNaoEncontradoException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class StatusHistoricoProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private StatusHistorico status;

    @Getter
    private enum StatusHistorico{
        VENDIDO("Vendido"),
        DEVOLVIDO("Devolvido"),
        ABATIDO("Abatido");

        private final String descricao;

        StatusHistorico(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public static StatusVenda.Status fromDescricao(String descricao) {
            for (StatusVenda.Status status : StatusVenda.Status.values()) {
                if (status.getDescricao().equalsIgnoreCase(descricao)) {
                    return status;
                }
            }
            throw new RegistroNaoEncontradoException("Status de produto inválido: " + descricao);
        }
    }

}
