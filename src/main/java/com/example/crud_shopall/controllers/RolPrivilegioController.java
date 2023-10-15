package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.RolPrivilegio;
import com.example.crud_shopall.services.RolPrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> newRolPrivilegio(@RequestBody RolPrivilegio rolPrivilegio){
        return this.rolPrivilegioService.newRolPrivilegio(rolPrivilegio);
    }

    @PutMapping(path = "{rolPrivilegioId}")
    public ResponseEntity<Object> updateRolPrivilegio(@RequestBody RolPrivilegio rolPrivilegio, @PathVariable("rolPrivilegioId") Long id){
        return this.rolPrivilegioService.updateRolPrivilegio(rolPrivilegio,id);
    }

    @DeleteMapping(path="{rolPrivilegioId}")
    public ResponseEntity<Object> deleteRolPrivilegio(@PathVariable("rolPrivilegioId") Long id){
        return this.rolPrivilegioService.deleteRolPrivilegio(id);
    }
}
