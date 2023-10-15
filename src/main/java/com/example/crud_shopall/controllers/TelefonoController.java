package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Telefono;
import com.example.crud_shopall.services.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/telefono")
public class TelefonoController {
    private final TelefonoService telefonoService;

    @Autowired
    public TelefonoController(TelefonoService telefonoService) {
        this.telefonoService = telefonoService;
    }

    @GetMapping("/all")
    public List<Telefono> getTelefono(){
        return this.telefonoService.getTelefono();
    }

    @PostMapping
    public ResponseEntity<Object> newtelefono(@RequestBody Telefono telefono){
        return this.telefonoService.newTelefono(telefono);
    }
    @PutMapping(path = "{telefonoId}")
    public ResponseEntity<Object> updateTelefono(@RequestBody Telefono telefono, @PathVariable("telefonoId") Long id){
        return this.telefonoService.updateTelefono(telefono,id);
    }

    @DeleteMapping(path="{telefonoId}")
    public ResponseEntity<Object> deleteTelefono(@PathVariable("telefonoId") Long id){
        return this.telefonoService.deleteTelefono(id);
    }
}
