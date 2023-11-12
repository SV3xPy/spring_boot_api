package com.example.crud_shopall.security;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.repositories.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    //@NonNull
    private final Usuario usuario;

    //@JsonIgnore
    private UsuarioRepository usuarioRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getCorreo();
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

    public String getNombre(){
        return usuario.getUsuario();
    }

    public List<Rol> getRolesByUser(){ return usuarioRepository.findRolesByUsuarioId(usuario.getId_usuario()); }
}
