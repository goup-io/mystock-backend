package com.goup.entities.lojas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table
public class LojaLogin implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @Column @NotNull
    private String user;
    @Column @NotNull
    private String senha;
    @JoinColumn @ManyToOne @NotNull
    private Loja loja;
    @Transient
    private TipoLogin tipoLogin;

    public LojaLogin(String user, String senhaEcrypted, Loja loja, TipoLogin tipoLogin) {
        this.user = user;
        this.senha = senhaEcrypted;
        this.loja = loja;
        this.tipoLogin = tipoLogin;
    }

    public LojaLogin() {

    }

    public Integer getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public Loja getLoja() {
        return loja;
    }

    public TipoLogin getTipoLogin() {
        return tipoLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipoLogin == TipoLogin.CAIXA) {
            return List.of(new SimpleGrantedAuthority("CAIXA"));
        } else  {
            return List.of(new SimpleGrantedAuthority("AREA_VENDA"));
        }
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getUser();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
