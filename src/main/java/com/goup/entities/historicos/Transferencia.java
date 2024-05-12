package com.goup.entities.historicos;

import com.goup.entities.estoque.ETP;
import com.goup.entities.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter
public class Transferencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private Integer quantidadeSolicitada;

    private Integer quantidadeLiberada;

    @NotNull @ManyToOne @JoinColumn(name = "coletor_id", referencedColumnName = "id")
    private Usuario coletor;

    @ManyToOne @JoinColumn(name = "liberador_id", referencedColumnName = "id")
    private Usuario liberador;

    @NotNull @ManyToOne @JoinColumn(name = "etp_id", referencedColumnName = "id")
    private ETP etp;
}
