package com.goup.entities.usuarios;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Login implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private int id;
    @Column
    private String user;
    @Column
    private String senha;
    @JoinColumn @OneToOne(cascade = CascadeType.REMOVE)
    private Usuario usuario;
    @Transient
    private UserRole role;

    public Login(String user, String senhaEcrypted, Usuario usuario, UserRole role) {
        this.user = user;
        this.senha = senhaEcrypted;
        this.usuario = usuario;
        this.role = role;
    }

    public Login() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.GERENTE || this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),  new SimpleGrantedAuthority("ROLE_USER"));
        } else  {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return user;
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
