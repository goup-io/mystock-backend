package com.goup.entities.usuarios.login;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Getter @Setter
@AllArgsConstructor
public class RedefinirSenha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn @ManyToOne
    private Login login;
    @NotNull
    private String token;
    @NotNull
    private boolean ativo;

    public RedefinirSenha() {}

    public RedefinirSenha(Login login, String token) {
        this.login = login;
        this.token = token;
    }
}
