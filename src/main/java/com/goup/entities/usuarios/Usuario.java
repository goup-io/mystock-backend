package com.goup.entities.usuarios;

import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer codigoVenda;
    @Size(min = 3, max = 30) @NotBlank @Column
    private String nome;
    @NotNull @JoinColumn @ManyToOne(cascade = CascadeType.PERSIST)
    private Cargo cargo;
    @Column(unique = true) @Email
    private String email;
    @NotNull @Column @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Número de celular inválido")
    private String telefone;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "loja_id")
    private Loja loja;

    @PostPersist
    public void setCodigoVenda() {
        this.codigoVenda = this.id + 100;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", codigoVenda=" + codigoVenda +
                ", nome='" + nome + '\'' +
                ", cargo=" + cargo +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", loja=" + loja +
                '}';
    }
}
