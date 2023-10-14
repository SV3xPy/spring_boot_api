package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Lada;
import com.example.crud_shopall.services.LadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/lada")
public class LadaController {
    private final LadaService ladaService;

    @Autowired
    public LadaController(LadaService ladaService) {
        this.ladaService = ladaService;
    }

    @GetMapping("/all")
    public List<Lada> getLada(){
        return this.ladaService.getLada();
    }
}