package com.goup.entities.lojas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoLoja {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer limiteEstoqueAlerta;
    @JoinColumn(name = "loja_id", referencedColumnName = "id") @OneToOne
    private Loja loja;
}
