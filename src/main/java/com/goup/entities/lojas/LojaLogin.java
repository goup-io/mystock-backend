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
    private String username;
    @Column @NotNull
    private String senha;
    @JoinColumn @ManyToOne @NotNull
    private Loja loja;
    @JoinColumn @ManyToOne @NotNull
    private AcessoLoja acessoLoja;
    @Transient
    private TipoLogin tipoLogin;

    public LojaLogin(String username, String senhaEcrypted, Loja loja, AcessoLoja acessoLoja, TipoLogin tipoLogin) {
        this.username = username;
        this.senha = senhaEcrypted;
        this.loja = loja;
        this.acessoLoja = acessoLoja;
        this.tipoLogin = tipoLogin;
    }

    public LojaLogin() {

    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public AcessoLoja getAcessoLoja() {
        return acessoLoja;
    }

    public void setAcessoLoja(AcessoLoja acessoLoja) {
        this.acessoLoja = acessoLoja;
    }

    public void setTipoLogin(TipoLogin tipoLogin) {
        this.tipoLogin = tipoLogin;
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
        return this.username;
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
