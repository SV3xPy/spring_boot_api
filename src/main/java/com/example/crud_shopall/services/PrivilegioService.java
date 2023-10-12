package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.EstadoRepository;
import com.example.crud_shopall.repositories.PrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegioService {

    private final PrivilegioRepository privilegioRepository;

    @Autowired
    public PrivilegioService(PrivilegioRepository privilegioRepository) {
        this.privilegioRepository = privilegioRepository;
    }

    public List<Privilegio> getPrivilegio(){
        return this.privilegioRepository.findAll();
    }
}
