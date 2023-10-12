package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> getEstado(){
        return this.estadoRepository.findAll();
    }
}
