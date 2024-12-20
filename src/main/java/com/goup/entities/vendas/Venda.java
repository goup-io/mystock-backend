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
    @NotNull @PastOrPresent
    private LocalDateTime dataHora;
    private Double desconto;
    private Double valorTotal;
    @NotNull @ManyToOne(cascade=CascadeType.ALL)
    private StatusVenda statusVenda;
    @NotNull @ManyToOne(cascade=CascadeType.ALL)
    private TipoVenda tipoVenda;
    @ManyToOne(cascade=CascadeType.ALL)
    private Usuario usuario;
}
