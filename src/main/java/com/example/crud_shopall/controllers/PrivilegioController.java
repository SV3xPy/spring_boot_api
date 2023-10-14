package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.PrivilegioRepository;
import com.example.crud_shopall.services.PrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/privilegio")
public class PrivilegioController {
    private final PrivilegioService privilegioService;

    @Autowired
    public PrivilegioController(PrivilegioService privilegioService) {
        this.privilegioService = privilegioService;
    }

    @GetMapping("/all")
    public List<Privilegio> getPrivilegio(){
        return this.privilegioService.getPrivilegio();
    }
}
