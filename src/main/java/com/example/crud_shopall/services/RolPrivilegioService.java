package com.example.crud_shopall.services;

import com.example.crud_shopall.model.RolPrivilegio;
import com.example.crud_shopall.repositories.RolPrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolPrivilegioService {
    private final RolPrivilegioRepository rolPrivilegioRepository;

    @Autowired
    public RolPrivilegioService(RolPrivilegioRepository rolPrivilegioRepository) {
        this.rolPrivilegioRepository = rolPrivilegioRepository;
    }

    public List<RolPrivilegio> getRolPrivilegio(){
        return this.rolPrivilegioRepository.findAll();
    }
}
