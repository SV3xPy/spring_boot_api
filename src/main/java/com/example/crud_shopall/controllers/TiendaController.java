package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tienda")
public class TiendaController {
    private final TiendaService tiendaService;
    @Autowired
    public TiendaController(TiendaService tiendaService){this.tiendaService =tiendaService;}
    @GetMapping()
    public List<Tienda> getTienda(){return this.tiendaService.getTienda();}
}
