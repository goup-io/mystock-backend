package com.goup.dtos.vendas.pagamento;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoPix {
    @NotBlank
    String nomeDestinatario;
    @NotNull
    String chaveDestinatario;
    @NotNull
    @Size(min = 0,max = 13)
    BigDecimal valor;
    @NotBlank
    String cidadeRemetente;
    @NotBlank
    String descricao;

    public String getValor(){
        return formatNumber(valor);
    }

    /*
     * Obtém um valor incluindo o ponto como separador de decimais e apenas 2 casas.
     */

    private static String formatNumber(final BigDecimal value){
        return String.format("%.2f", value).formatted().replace(",", ".");
    }

}
