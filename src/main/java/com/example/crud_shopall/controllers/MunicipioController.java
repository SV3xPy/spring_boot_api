package com.example.crud_shopall.controllers;


import com.example.crud_shopall.model.Municipio;

import com.example.crud_shopall.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/municipio")
public class MunicipioController {
    private final MunicipioService municipioService;
    @Autowired
    public MunicipioController(MunicipioService municipioService){this.municipioService =municipioService;}
    @GetMapping()
    public List<Municipio> getMunicipio(){return this.municipioService.getMunicipio();}
    @PostMapping
    public ResponseEntity<Object> newMunicipio(@RequestBody Municipio municipio){
        return this.municipioService.newMunicipio(municipio);
    }
    @PutMapping(path = "{municipioId}")
    public ResponseEntity<Object> updateMunicipio(@RequestBody Municipio municipio, @PathVariable("municipioId") Long id){
        return this.municipioService.updateMunicipio(municipio,id);
    }

    @DeleteMapping(path="{municipioId}")
    public ResponseEntity<Object> deleteMunicipio(@PathVariable("municipioId") Long id){
        return this.municipioService.deleteMunicipio(id);
    }
}
