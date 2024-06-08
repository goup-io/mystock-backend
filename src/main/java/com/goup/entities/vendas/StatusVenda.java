package com.goup.entities.vendas;

import com.goup.exceptions.RegistroNaoEncontradoException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class StatusVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true) @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
   public enum Status{
        PENDENTE("Pendente"),
        FINALIZADA("Finalizada"),
        CANCELADA("Cancelada");

        private final String descricao;

        Status(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public static Status fromDescricao(String descricao) {
            for (Status status : Status.values()) {
                if (status.getDescricao().equalsIgnoreCase(descricao)) {
                    return status;
                }
            }
            throw new RegistroNaoEncontradoException("Status de venda inválido: " + descricao);
        }
    }
}
