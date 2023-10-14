package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.Telefono;
import com.example.crud_shopall.services.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/telefono")
public class TelefonoController {
    private final TelefonoService telefonoService;

    @Autowired
    public TelefonoController(TelefonoService telefonoService) {
        this.telefonoService = telefonoService;
    }

    @GetMapping("/all")
    public List<Telefono> getTelefono(){
        return this.telefonoService.getTelefono();
    }
}
