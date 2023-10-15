package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.VentaDetalle;
import com.example.crud_shopall.services.EstadoService;
import com.example.crud_shopall.services.VentaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ventadetalle")
public class VentaDetalleController {
    private final VentaDetalleService ventaDetalleService;

    @Autowired //Señala que es una inyección de dependencias
    public VentaDetalleController(VentaDetalleService ventaDetalleService) {
        this.ventaDetalleService = ventaDetalleService;
    }

    @GetMapping
    public List<VentaDetalle> getVentaDetalle(){
        return this.ventaDetalleService.getVentaDetalle();
    }
    @PostMapping
    public ResponseEntity<Object> newVentaDetalle(@RequestBody VentaDetalle ventaDetalle){
        return this.ventaDetalleService.newVentaDetalle(ventaDetalle);
    }
    @PutMapping(path = "{ventadetalleId}")
    public ResponseEntity<Object> updateVentaDetalle(@RequestBody VentaDetalle ventaDetalle,@PathVariable("ventadetalleId") Long id){
        return this.ventaDetalleService.updateVentaDetalle(ventaDetalle,id);
    }
    @DeleteMapping(path="{ventadetalleId}")
    public ResponseEntity<Object> deleteVentaDetalle(@PathVariable("ventadetalleId") Long id){
        return this.ventaDetalleService.deleteVentaDetalle(id);
    }
}
