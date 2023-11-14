package com.example.crud_shopall.controllers;
import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.VentaOperacion;
import com.example.crud_shopall.services.VentaOperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/venta_operacion")
public class VentaOperacionController {
    private final VentaOperacionService ventaOperacionService;

    @Autowired
    public VentaOperacionController(VentaOperacionService ventaOperacionService) {
        this.ventaOperacionService = ventaOperacionService;
    }

    @GetMapping("/all")
    public List<VentaOperacion> getVenta_operacion(){
        return this.ventaOperacionService.getVenta_operacion();
    }

    @PostMapping
    public ResponseEntity<Object> addVenta_operacion(@RequestBody VentaOperacion venta_operacion){
        return this.ventaOperacionService.addVenta_operacion(venta_operacion);
    }

    @PutMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> updateVenta(@RequestBody VentaOperacion venta_operacion, @PathVariable("tipo_pagoId") Long id){
        return this.ventaOperacionService.updateVenta_operacion(venta_operacion, id);
    }

    @DeleteMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> deleteVenta_operacion(@PathVariable("id") Long id){
        return this.ventaOperacionService.deleteVenta_operacion(id);
    }
}
