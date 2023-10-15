package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
        import com.example.crud_shopall.model.Venta;
        import com.example.crud_shopall.services.VentaService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("api/v1/telefono")
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

    @PostMapping("/add")
    public Venta addVenta(@RequestBody Venta venta){
        return this.ventaService.setVenta(venta);
    }

    @PutMapping("/update")
    public Venta updateVenta(@RequestBody Venta venta){
        return this.ventaService.setVenta(venta);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVenta(@PathVariable("id") Long id){
        this.ventaService.deleteVenta(id);
    }
}
