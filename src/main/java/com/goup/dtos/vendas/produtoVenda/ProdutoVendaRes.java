package com.goup.dtos.vendas.produtoVenda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.vendas.venda.VendaRes;
import com.goup.entities.historicos.StatusHistoricoProduto;
import com.goup.entities.vendas.ItemPromocional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendaRes{
        private Integer id;
        private Double valorUnitario;
        private Integer quantidade;
        private Double desconto;
        private ItemPromocional itemPromocional;
        private VendaRes venda;
        private ETPTableRes etp;
        private List<HistoricoProdutoListagem> historicos;

        @Getter @Setter
        public static class HistoricoProdutoListagem{
            private Integer id;
            private @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora;
            private StatusHistoricoProduto statusHistoricoProduto;
        }


}
