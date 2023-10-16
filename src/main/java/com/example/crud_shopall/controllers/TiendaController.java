package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tienda")
public class TiendaController {
    private final TiendaService tiendaService;
    @Autowired
    public TiendaController(TiendaService tiendaService){this.tiendaService =tiendaService;}
    @GetMapping()
    public List<Tienda> getTienda(){return this.tiendaService.getTienda();}
    @PostMapping
    public ResponseEntity<Object> newTienda(@RequestBody Tienda tienda){
        return this.tiendaService.newTienda(tienda);
    }
    @PutMapping(path = "{tiendaId}")
    public ResponseEntity<Object> updateTienda(@RequestBody Tienda tienda, @PathVariable("tiendaId") Long id){
        return this.tiendaService.updateTienda(tienda,id);
    }

    @DeleteMapping(path="{tiendaId}")
    public ResponseEntity<Object> deleteTienda(@PathVariable("tiendaId") Long id){
        return this.tiendaService.deleteTienda(id);
    }
}
