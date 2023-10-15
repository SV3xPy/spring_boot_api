package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Localidad;
import com.example.crud_shopall.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
@Service

public class LocalidadService {
    private final LocalidadRepository localidadRepository;

    @Autowired
    public LocalidadService(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    public List<Localidad> getLocalidad() {
        return this.localidadRepository.findAll();
    }
}
