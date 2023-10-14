package com.example.crud_shopall.controllers;

import com.example.crud_shopall.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/marca")
public class MarcaController
{
    private final MarcaService marcaService;

    @Autowired
    public MarcaController (MarcaService marcaService)
    {
        this.marcaService = marcaService;
    }
}
