package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
        import com.example.crud_shopall.model.Venta_operacion;
        import com.example.crud_shopall.services.Venta_operacionService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("api/v1/venta_operacion")
public class Venta_operacionController {
    private final Venta_operacionService venta_operacionService;

    @Autowired
    public Venta_operacionController(Venta_operacionService venta_operacionService) {
        this.venta_operacionService = venta_operacionService;
    }

    @GetMapping("/all")
    public List<Venta_operacion> getVenta_operacion(){
        return this.venta_operacionService.getVenta_operacion();
    }

    @PostMapping
    public ResponseEntity<Object> addVenta_operacion(@RequestBody Venta_operacion venta_operacion){
        return this.venta_operacionService.addVenta_operacion(venta_operacion);
    }

    @PutMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> updateVenta(@RequestBody Venta_operacion venta_operacion, @PathVariable("tipo_pagoId") Long id){
        return this.venta_operacionService.updateVenta_operacion(venta_operacion, id);
    }

    @DeleteMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> deleteVenta_operacion(@PathVariable("id") Long id){
        return this.venta_operacionService.deleteVenta_operacion(id);
    }
}
