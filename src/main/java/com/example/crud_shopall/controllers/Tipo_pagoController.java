package com.example.crud_shopall.controllers;

        import com.example.crud_shopall.model.Tipo_pago;
        import com.example.crud_shopall.services.Tipo_pagoService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("api/v1/lada")
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

    @PostMapping("/add")
    public Tipo_pago addTipo_pago(@RequestBody Tipo_pago tipo_pago){
        return this.tpService.setTipo_pago(tipo_pago);
    }

    @PutMapping("/update")
    public Tipo_pago updateTipo_pago(@RequestBody Tipo_pago tipo_pago){
        return this.tpService.setTipo_pago(tipo_pago);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTipo_pago(@PathVariable("id") Long id){
        this.tpService.deleteTipo_pago(id);
    }
}