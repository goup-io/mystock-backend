package com.goup.dtos.historico.produto;

import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.vendas.produtoVenda.ProdutoVendaRes;
import com.goup.dtos.vendas.venda.VendaRes;
import com.goup.entities.historicos.StatusHistoricoProduto;
import com.goup.entities.vendas.ProdutoVenda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class HistoricoProdutoRes{
    Integer id;
    LocalDateTime dataHora;
    StatusHistoricoProduto statusHistoricoProduto;
    ProdutoVendaListagem produtoVenda;

    @Getter @Setter
    public static class ProdutoVendaListagem{
        private Integer id;
        private Double valorUnitario;
        private Integer quantidade;
        private Double desconto;
        private String itemPromocional;
        private VendaRes venda;
        private ETPTableRes etp;
    }
}
