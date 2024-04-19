package com.goup.entities.usuarios.login;

import com.goup.entities.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Login implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private int id;
    @Column @NotNull
    private String username;
    @Column @NotNull
    private String senha;
    @NotNull @JoinColumn @OneToOne(cascade = CascadeType.REMOVE)
    private Usuario usuario;
    @Transient
    private UserRole role;

    public Login(String username, String senhaEcrypted, Usuario usuario, UserRole role) {
        this.username = username;
        this.senha = senhaEcrypted;
        this.usuario = usuario;
        this.role = role;
    }

    public Login() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMIN"),  new SimpleGrantedAuthority("GERENTE"), new SimpleGrantedAuthority("VENDEDOR"));
        } else if(this.role == UserRole.GERENTE){
            return List.of(new SimpleGrantedAuthority("GERENTE"), new SimpleGrantedAuthority("VENDEDOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("VENDEDOR"));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setUsername(String user) {
        this.username = user;
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
        return username;
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
