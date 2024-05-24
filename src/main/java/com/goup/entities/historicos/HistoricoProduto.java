package com.goup.entities.historicos;

import com.goup.entities.vendas.ProdutoVenda;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class HistoricoProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @PastOrPresent
    private LocalDateTime dataHora;
    @JoinColumn @OneToOne
    private ProdutoVenda produtoVenda;
    @JoinColumn @ManyToOne
    private StatusHistoricoProduto statusHistoricoProduto;

}
