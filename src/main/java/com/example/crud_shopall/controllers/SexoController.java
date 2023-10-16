package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Sexo;
import com.example.crud_shopall.services.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sexo")
public class SexoController {
    private final SexoService sexoService;

    @Autowired
    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping("/all")
    public List<Sexo> getSexo(){
        return this.sexoService.getSexo();
    }

    @PostMapping
    public ResponseEntity<Object> newSexo(@RequestBody Sexo sexo){
        return this.sexoService.newSexo(sexo);
    }
    @PutMapping(path = "{sexoId}")
    public ResponseEntity<Object> updateSexo(@RequestBody Sexo sexo,@PathVariable("sexoId") Long id){
        return this.sexoService.updateSexo(sexo,id);
    }
    @DeleteMapping(path="{sexoId}")
    public ResponseEntity<Object> deleteSexo(@PathVariable("sexoId") Long id){
        return this.sexoService.deleteSexo(id);
    }
}
