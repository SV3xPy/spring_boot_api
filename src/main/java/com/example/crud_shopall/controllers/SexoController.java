package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Sexo;
import com.example.crud_shopall.services.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sexo")
public class SexoController {
    private final SexoService sexoService;

    @Autowired
    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping("/all")
    public List<Sexo> getSexo(){
        return this.sexoService.getSexo();
    }
}
