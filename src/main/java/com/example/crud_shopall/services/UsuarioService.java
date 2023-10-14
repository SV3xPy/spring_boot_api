package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuario(){
        return this.usuarioRepository.findAll();
    }
}
