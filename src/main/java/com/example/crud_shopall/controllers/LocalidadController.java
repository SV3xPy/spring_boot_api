package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Localidad;
import com.example.crud_shopall.model.Localidad;
import com.example.crud_shopall.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/localidad")
public class LocalidadController {
    private final LocalidadService localidadService;
    @Autowired
    public LocalidadController(LocalidadService localidadService){this.localidadService = localidadService;}
    @GetMapping()
    public List<Localidad> getLocalidad(){return this.localidadService.getLocalidad();}
    @PostMapping
    public ResponseEntity<Object> newLocalidad(@RequestBody Localidad localidad){
        return this.localidadService.newLocalidad(localidad);
    }
    @PutMapping(path = "{localidadId}")
    public ResponseEntity<Object> updateLocalidad(@RequestBody Localidad localidad, @PathVariable("localidadId") Long id){
        return this.localidadService.updateLocalidad(localidad,id);
    }

    @DeleteMapping(path="{localidadId}")
    public ResponseEntity<Object> deleteLocalidad(@PathVariable("localidadId") Long id){
        return this.localidadService.deleteLocalidad(id);
    }
}
