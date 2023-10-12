package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> getRol(){
        return this.rolRepository.findAll();
    }
}
