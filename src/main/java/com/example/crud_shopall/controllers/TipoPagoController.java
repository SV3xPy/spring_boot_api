package com.example.crud_shopall.controllers;
import com.example.crud_shopall.model.TipoPago;
import com.example.crud_shopall.services.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/tipo_pago")
public class TipoPagoController {
    private final TipoPagoService tpService;

    @Autowired
    public TipoPagoController(TipoPagoService tpService) {
        this.tpService = tpService;
    }

    @GetMapping("/all")
    public List<TipoPago> getTipo_pago(){
        return this.tpService.getTipoPago();
    }

    @PostMapping
    public ResponseEntity<Object> addTipo_pago(@RequestBody TipoPago tipo_pago){
        return this.tpService.addTipoPago(tipo_pago);
    }

    @PutMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> updateTipo_pago(@RequestBody TipoPago tipo_pago,  @PathVariable("tipo_pagoId") Long id){
        return this.tpService.updateTipoPago(tipo_pago, id);
    }

    @DeleteMapping(path = "{tipo_pagoId}")
    public ResponseEntity<Object> deleteTipo_pago(@PathVariable("tipo_pagoId") Long id){
        return this.tpService.deleteTipoPago(id);
    }
}
