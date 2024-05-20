package com.goup.entities.vendas;

import com.goup.entities.estoque.ETP;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProdutoVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valorUnitario;
    private Integer quantidade;
    private Double desconto;
    @Enumerated(EnumType.ORDINAL)
    private ItemPromocional itemPromocional;
    @JoinColumn @ManyToOne
    private Venda venda;
    @JoinColumn @ManyToOne
    private ETP etp;
}
