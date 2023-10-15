package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Localidad;
import com.example.crud_shopall.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/localidad")
public class LocalidadController {
    private final LocalidadService localidadService;
    @Autowired
    public LocalidadController(LocalidadService localidadService){this.localidadService = localidadService;}
    @GetMapping()
    public List<Localidad> getLocalidad(){return this.localidadService.getLocalidad();}
}
