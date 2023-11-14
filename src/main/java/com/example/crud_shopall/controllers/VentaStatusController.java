package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.VentaDetalle;
import com.example.crud_shopall.model.VentaStatus;
import com.example.crud_shopall.services.EstadoService;
import com.example.crud_shopall.services.VentaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ventastatus")
public class VentaStatusController {
    private final VentaStatusService ventaStatusService;

    @Autowired //Señala que es una inyección de dependencias
    public VentaStatusController(VentaStatusService ventaStatusService) {
        this.ventaStatusService = ventaStatusService;
    }

    @GetMapping
    public List<VentaStatus> getVentaStatus(){
        return this.ventaStatusService.getVentaStatus();
    }
    @PostMapping
    public ResponseEntity<Object> newVentaStatus(@RequestBody VentaStatus ventaStatus){
        return this.ventaStatusService.newVentaStatus(ventaStatus);
    }
    @PutMapping(path = "{ventastatusId}")
    public ResponseEntity<Object> updateVentaStatus(@RequestBody VentaStatus ventaStatus,@PathVariable("ventastatusId") Long id){
        return this.ventaStatusService.updateVentaStatus(ventaStatus,id);
    }
    @DeleteMapping(path="{ventastatusId}")
    public ResponseEntity<Object> deleteVentaStatus(@PathVariable("ventastatusId") Long id){
        return this.ventaStatusService.deleteVentaStatus(id);
    }
}
