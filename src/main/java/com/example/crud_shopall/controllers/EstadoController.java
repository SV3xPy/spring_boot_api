package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estado")
public class EstadoController {
    private final EstadoService estadoService;

    @Autowired //Señala que es una inyección de dependencias
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> getEstado(){
        return this.estadoService.getEstado();
    }
    @PostMapping
    public ResponseEntity<Object> newEstado(@RequestBody Estado estado){
        return this.estadoService.newEstado(estado);
    }
    @PutMapping(path = "{estadoId}")
    public ResponseEntity<Object> updateEstado(@RequestBody Estado estado,@PathVariable("estadoId") Long id){
        return this.estadoService.updateEstado(estado,id);
    }
    @DeleteMapping(path="{estadoId}")
    public ResponseEntity<Object> deleteEstado(@PathVariable("estadoId") Long id){
        return this.estadoService.deleteEstado(id);
    }
}
