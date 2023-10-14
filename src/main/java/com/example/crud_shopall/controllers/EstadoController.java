package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/estado")
public class EstadoController {
    private final EstadoService estadoService;

    @Autowired //Señala que es una inyección de dependencias
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping("/all")
    public List<Estado> getEstado(){
        return this.estadoService.getEstado();
    }
}