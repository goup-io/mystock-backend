package com.goup.entities.vendas;

import com.goup.entities.estoque.ETP;
import com.goup.entities.historicos.HistoricoProduto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class ProdutoVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valorUnitario;
    private Integer quantidade;
    private Double desconto;
//    @Enumerated(EnumType.ORDINAL)
//    private ItemPromocional itemPromocional;
    @JoinColumn @ManyToOne
    private Venda venda;
    @JoinColumn @ManyToOne
    private ETP etp;
    @OneToMany(mappedBy = "produtoVenda")
    private List<HistoricoProduto> historicoProduto;
}
