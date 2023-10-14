package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Sexo;
import com.example.crud_shopall.repositories.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexoService {
    private final SexoRepository sexoRepository;

    @Autowired
    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<Sexo> getSexo(){
        return this.sexoRepository.findAll();
    }
}
