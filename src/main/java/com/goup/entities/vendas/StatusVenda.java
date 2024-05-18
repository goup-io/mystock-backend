package com.goup.entities.vendas;

import com.goup.exceptions.RegistroNaoEncontradoException;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum StatusVenda {
    EM_ANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusVenda(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusVenda fromDescricao(String descricao) {
        for (StatusVenda status : StatusVenda.values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new RegistroNaoEncontradoException("Status de venda inválido: " + descricao);
    }
}
