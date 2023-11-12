package com.example.crud_shopall.security;

import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findOneByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException("El usuario con email " + email + " no existe."));
        return new UserDetailsImpl(usuario, usuarioRepository);
    }
}
