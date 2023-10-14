package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.RolPrivilegio;
import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.model.UsuarioRol;
import com.example.crud_shopall.services.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuariorol")
public class UsuarioRolController {

    private final UsuarioRolService usuarioRolService;

    @Autowired
    public UsuarioRolController(UsuarioRolService usuarioRolService) {
        this.usuarioRolService = usuarioRolService;
    }

    @GetMapping("/all")
    public List<UsuarioRol> getUsuarioRol(){
        return this.usuarioRolService.getUsuarioRol();
    }
}
