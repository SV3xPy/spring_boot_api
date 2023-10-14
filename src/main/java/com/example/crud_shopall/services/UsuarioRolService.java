package com.example.crud_shopall.services;

import com.example.crud_shopall.model.UsuarioRol;
import com.example.crud_shopall.repositories.UsuarioRolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolService {
    private final UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolService(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

    public List<UsuarioRol> getUsuarioRol(){
        return this.usuarioRolRepository.findAll();
    }
}
