package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rol")
public class RolController {
    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/all")
    public List<Rol> getRol(){
        return this.rolService.getRol();
    }

    @PostMapping
    public ResponseEntity<Object> newRol(@RequestBody Rol rol){
        return this.rolService.newRol(rol);
    }
    @PutMapping(path = "{rolId}")
    public ResponseEntity<Object> updateRol(@RequestBody Rol rol,@PathVariable("rolId") Long id){
        return this.rolService.updateRol(rol,id);
    }
    @DeleteMapping(path="{rolId}")
    public ResponseEntity<Object> deleteRol(@PathVariable("rolId") Long id){
        return this.rolService.deleteRol(id);
    }
}
