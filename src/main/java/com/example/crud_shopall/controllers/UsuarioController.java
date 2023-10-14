package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public List<Usuario> getUsuario(){
        return this.usuarioService.getUsuario();
    }
}
