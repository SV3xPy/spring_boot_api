package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.RolPrivilegio;
import com.example.crud_shopall.services.RolPrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rolprivilegio")
public class RolPrivilegioController {
    private final RolPrivilegioService rolPrivilegioService;

    @Autowired
    public RolPrivilegioController(RolPrivilegioService rolPrivilegioService) {
        this.rolPrivilegioService = rolPrivilegioService;
    }

    @GetMapping("/all")
    public List<RolPrivilegio> getRolPrivilegio(){
        return this.rolPrivilegioService.getRolPrivilegio();
    }
}
