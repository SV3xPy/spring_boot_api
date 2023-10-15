package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Municipio;
import com.example.crud_shopall.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {
    private final MunicipioRepository municipioRepository;
    @Autowired
    public MunicipioService (MunicipioRepository municipioRepository){this.municipioRepository = municipioRepository;}
    public List<Municipio> getMunicipio(){return this.municipioRepository.findAll();}
}
