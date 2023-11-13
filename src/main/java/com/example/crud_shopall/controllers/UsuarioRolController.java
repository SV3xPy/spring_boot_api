package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.UsuarioRol;
import com.example.crud_shopall.services.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> newUsuarioRol(@RequestBody UsuarioRol usuarioRol){
        return this.usuarioRolService.newUsuarioRol(usuarioRol);
    }
    @PutMapping(path = "{usuarioRolId}")
    public ResponseEntity<Object> updateUsuarioRol(@RequestBody UsuarioRol usuarioRol, @PathVariable("usuarioRolId") Long id){
        return this.usuarioRolService.updateUsuarioRol(usuarioRol,id);
    }

    @DeleteMapping(path="{usuarioRolId}")
    public ResponseEntity<Object> deleteUsuarioRol(@PathVariable("usuarioRolId") Long id){
        return this.usuarioRolService.deleteUsuarioRol(id);
    }
}
