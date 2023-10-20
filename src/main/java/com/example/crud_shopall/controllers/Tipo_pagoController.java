package com.example.crud_shopall.controllers;


import com.example.crud_shopall.model.Tipo_pago;
import com.example.crud_shopall.services.Tipo_pagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tipo_pago")
public class Tipo_pagoController {
    private final Tipo_pagoService tpService;

    @Autowired
    public Tipo_pagoController(Tipo_pagoService tpService) {
        this.tpService = tpService;
    }

    @GetMapping("/all")
    public List<Tipo_pago> getTipo_pago(){
        return this.tpService.getTipo_pago();
    }

    @PostMapping
    public ResponseEntity<Object> addTipo_pago(@RequestBody Tipo_pago tipo_pago){
        return this.tpService.addTipo_pago(tipo_pago);
    }

    @PutMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> updateTipo_pago(@RequestBody Tipo_pago tipo_pago,  @PathVariable("tipo_pagoId") Long id){
        return this.tpService.updateTipo_pago(tipo_pago, id);
    }

    @DeleteMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> deleteTipo_pago(@PathVariable("tipo_pagoId") Long id){
        return this.tpService.deleteTipo_pago(id);
    }
}