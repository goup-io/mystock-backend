package com.goup.entities.vendas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Pagamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn @ManyToOne
    private TipoPagamento tipoPagamento;
    @JoinColumn @ManyToOne
    private Venda venda;
    private Double valor;
    private Integer qtdParcelas;


}
