package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.Lada;
import com.example.crud_shopall.services.LadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lada")
public class LadaController {
    private final LadaService ladaService;

    @Autowired
    public LadaController(LadaService ladaService) {
        this.ladaService = ladaService;
    }

    @GetMapping("/all")
    public List<Lada> getLada(){
        return this.ladaService.getLada();
    }

    @PostMapping
    public ResponseEntity<Object> newLada(@RequestBody Lada lada){
        return this.ladaService.newLada(lada);
    }

    @PutMapping(path = "{ladaId}")
    public ResponseEntity<Object> updateLada(@RequestBody Lada lada,@PathVariable("ladaId") Long id){
        return this.ladaService.updateLada(lada,id);
    }

    @DeleteMapping(path="{ladaId}")
    public ResponseEntity<Object> deleteLada(@PathVariable("ladaId") Long id){
        return this.ladaService.deleteLada(id);
    }
}
