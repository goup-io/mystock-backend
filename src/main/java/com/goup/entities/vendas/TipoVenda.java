package com.goup.entities.vendas;

import com.goup.exceptions.RegistroNaoEncontradoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TipoVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @Column(unique = true) @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @NotNull
    private Double desconto;

    @Getter
    public enum Tipo{
        VAREJO("Varejo"),
        ATACADO("Atacado"),
        ESPECIAL("Especial");

        private final String tipo;

        Tipo(String tipo) {
            this.tipo = tipo;
        }

        public static TipoVenda.Tipo fromDescricao(String descricao) {
            for (TipoVenda.Tipo tipo : TipoVenda.Tipo.values()) {
                if (tipo.tipo.equalsIgnoreCase(descricao)) {
                    return tipo;
                }
            }
            throw new RegistroNaoEncontradoException("Tipo de pagamento inválido: " + descricao);
        }
    }
}
