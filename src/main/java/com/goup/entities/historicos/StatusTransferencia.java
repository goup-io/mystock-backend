package com.goup.entities.historicos;

import com.goup.entities.vendas.StatusVenda;
import com.goup.exceptions.RegistroNaoEncontradoException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class StatusTransferencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true) @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    public enum Status{
        PENDENTE("Pendente"),
        ACEITO("Aceito"),
        NEGADO("Negado");

        private final String descricao;

        Status(String descricao) {
            this.descricao = descricao;
        }

        public static Status fromDescricao(String descricao){
            for(Status status : Status.values()){
                if(status.getDescricao().equalsIgnoreCase(descricao)){
                    return status;
                }
            }
            throw new RegistroNaoEncontradoException("Status de Transferência inválido: " + descricao);
        }
    }

}
