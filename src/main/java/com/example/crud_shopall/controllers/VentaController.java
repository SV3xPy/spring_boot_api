package com.example.crud_shopall.controllers;
import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.Venta;
import com.example.crud_shopall.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/venta")
public class VentaController {
    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/all")
    public List<Venta> getVenta(){
        return this.ventaService.getVenta();
    }

    @PostMapping
    public ResponseEntity<Object> addVenta(@RequestBody Venta venta){

        return this.ventaService.addVenta(venta);
    }

    @PutMapping(path = "{ventaId}")
    public ResponseEntity<Object> updateVenta(@RequestBody Venta venta,  @PathVariable("ventaId") Long id){
        return this.ventaService.updateVenta(venta, id);
    }

    @DeleteMapping(path = "{ventaId}")
    public ResponseEntity<Object> deleteVenta( @PathVariable("ventaId") Long id){
        return this.ventaService.deleteVenta(id);
    }
}
