package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Municipio;
import com.example.crud_shopall.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/municipio")
public class MunicipioController {
    private final MunicipioService municipioService;
    @Autowired
    public MunicipioController(MunicipioService municipioService){this.municipioService =municipioService;}
    @GetMapping()
    public List<Municipio> getMunicipio(){return this.municipioService.getMunicipio();}
}
