package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.Telefono;
import com.example.crud_shopall.repositories.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefonoService {
    private final TelefonoRepository telefonoRepository;

    @Autowired
    public TelefonoService(TelefonoRepository telefonoRepository) {
        this.telefonoRepository = telefonoRepository;
    }

    public List<Telefono> getTelefono(){
        return this.telefonoRepository.findAll();
    }
}
