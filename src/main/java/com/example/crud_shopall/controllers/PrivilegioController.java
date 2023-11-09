package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.PrivilegioRepository;
import com.example.crud_shopall.services.PrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/privilegio")
public class PrivilegioController {
    private final PrivilegioService privilegioService;

    @Autowired
    public PrivilegioController(PrivilegioService privilegioService) {
        this.privilegioService = privilegioService;
    }

    @GetMapping("/all")
    public List<Privilegio> getPrivilegio(){
        return this.privilegioService.getPrivilegio();
    }

    @PostMapping
    public ResponseEntity<Object> newPrivilegio(@RequestBody Privilegio privilegio){
        return this.privilegioService.newPrivilegio(privilegio);
    }
    @PutMapping(path = "{privilegioId}")
    public ResponseEntity<Object> updatePrivilegio(@RequestBody Privilegio privilegio,@PathVariable("privilegioId") Long id){
        return this.privilegioService.updatePrivilegio(privilegio,id);
    }
    @DeleteMapping(path="{privilegioId}")
    public ResponseEntity<Object> deletePrivilegio(@PathVariable("privilegioId") Long id){
        return this.privilegioService.deletePrivilegio(id);
    }
}
