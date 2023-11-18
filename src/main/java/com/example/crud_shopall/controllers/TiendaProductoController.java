package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.TiendaProducto;
import com.example.crud_shopall.services.TiendaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tiendaProducto")
public class TiendaProductoController {
    private final TiendaProductoService tiendaProductoService;
    @Autowired
    public TiendaProductoController(TiendaProductoService tiendaProductoService){
        this.tiendaProductoService = tiendaProductoService;
    }
    @GetMapping("/all")
    public List<TiendaProducto> getTiendaProducto(){
        return this.tiendaProductoService.getTiendaProducto();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> registrarTiendaProducto(@RequestBody TiendaProducto tiendaProducto){
        return this.tiendaProductoService.newTiendaProducto(tiendaProducto);
    }

    @PutMapping("/update/{tiendaProductoID}")
    public ResponseEntity<Object> actualizarTiendaProducto(@PathVariable("tiendaProductoID")Long id, @RequestBody TiendaProducto tiendaProducto){
        return this.tiendaProductoService.updateTiendaProducto(tiendaProducto,id);
    }

    @DeleteMapping("/delete/{tiendaProductoID}")
    public ResponseEntity<Object> eliminarTiendaProducto(@PathVariable("tiendaProductoID")Long id){
        return this.tiendaProductoService.deleteTiendaProducto(id);
    }
}
