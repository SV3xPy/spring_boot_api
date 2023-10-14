package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Lada;
import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.LadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LadaService {
    private final LadaRepository ladaRepository;

    @Autowired
    public LadaService(LadaRepository ladaRepository) {
        this.ladaRepository = ladaRepository;
    }

    public List<Lada> getLada(){
        return this.ladaRepository.findAll();
    }
}
