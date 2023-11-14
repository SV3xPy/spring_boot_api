package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Marca;
import com.example.crud_shopall.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/marca")
public class MarcaController
{
    private final MarcaService marcaService;

    @Autowired
    public MarcaController (MarcaService marcaService)
    {
        this.marcaService = marcaService;
    }

    @GetMapping("/all")
    public List <Marca> getMarca()
    {
        return this.marcaService.getMarca();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarMarca (@RequestBody Marca marca)
    {
        return this.marcaService.newMarca(marca);
    }

    @PutMapping("/update/{marcaID}")
    public ResponseEntity<Object> actualizarMarca (@RequestBody Marca marca, @PathVariable("marcaID") Long id)
    {
        return this.marcaService.updateMarca(marca, id);
    }

    @DeleteMapping(path = "/delete/{marcaID}")
    public ResponseEntity<Object> eliminarMarca(@PathVariable("marcaID") Long id)
    {
        return this.marcaService.deleteMarca(id);
    }
}
