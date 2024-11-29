package com.goup.entities.vendas;

import com.goup.exceptions.RegistroNaoEncontradoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TipoPagamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true) @NotNull @NotBlank @Enumerated(EnumType.STRING)
    private MetodoPagamento metodo;

    @Getter
    public enum MetodoPagamento{
        CREDITO("Credito"),
        DEBITO("Debito"),
        PIX("PIX"),
        DINHEIRO("Dinheiro");

        private final String metodo;

        MetodoPagamento(String metodo) {
            this.metodo = metodo;
        }

        public static TipoPagamento.MetodoPagamento fromDescricao(String descricao) {
            for (TipoPagamento.MetodoPagamento metodo : TipoPagamento.MetodoPagamento.values()) {
                if (metodo.getMetodo().equalsIgnoreCase(descricao)) {
                    return metodo;
                }
            }
            throw new RegistroNaoEncontradoException("Tipo de pagamento inválido: " + descricao);
        }
    }
}
