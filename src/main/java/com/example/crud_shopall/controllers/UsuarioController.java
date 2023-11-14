package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //@PreAuthorize("hasRole('Administrador')")
    @GetMapping("/all")
    public List<Usuario> getUsuario(){
        return this.usuarioService.getUsuario();
    }

    //@PreAuthorize("hasRole('Administrador')")
    @PostMapping
    public ResponseEntity<Object> newUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.newUsuario(usuario);
    }

    //@PreAuthorize("hasRole('Administrador')")
    @PutMapping(path = "{usuarioId}")
    public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario,@PathVariable("usuarioId") Long id){
        return this.usuarioService.updateUsuario(usuario,id);
    }

    //@PreAuthorize("hasRole('Administrador')")
    @DeleteMapping(path="{usuarioId}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable("usuarioId") Long id){
        return this.usuarioService.deleteUsuario(id);
    }

    //@PreAuthorize("hasRole('Administrador') or hasRole('Vendedor') or hasRole('Comprador')")
    @GetMapping("/{usuarioId}/roles")
    public ResponseEntity<Object> getUsuarioRoles(@PathVariable("usuarioId") Long id){
        return this.usuarioService.getUsuarioRoles(id);
    }
}
