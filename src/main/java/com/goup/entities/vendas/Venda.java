package com.goup.entities.vendas;
import com.goup.entities.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Venda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @PastOrPresent @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime dataHora;
    private Double desconto;
    @NotNull @Positive
    private Double valorTotal;
    @NotNull @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda;
    @NotNull @ManyToOne
    private TipoVenda tipoVenda;
    @NotNull @ManyToOne
    private Usuario usuario;
}
