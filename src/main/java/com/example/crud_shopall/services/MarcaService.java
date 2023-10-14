package com.example.crud_shopall.services;

import com.example.crud_shopall.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService
{
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService (MarcaRepository marcaRepository)
    {
        this.marcaRepository = marcaRepository;
    }

    public List <Marca> getMarca ()
    {
        return this.marcaRepository.findAll();
    }
}
