package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.Resenia;
import com.example.crud_shopall.services.EstadoService;
import com.example.crud_shopall.services.ReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resenia")
public class ReseniaController {
    private final ReseniaService reseniaService;

    @Autowired //Señala que es una inyección de dependencias
    public ReseniaController(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    @GetMapping
    public List<Resenia> getResenia(){
        return this.reseniaService.getResenia();
    }
    @PostMapping
    public ResponseEntity<Object> newResenia(@RequestBody Resenia resenia){
        return this.reseniaService.newResenia(resenia);
    }
    @PutMapping(path = "{reseniaId}")
    public ResponseEntity<Object> updateResenia(@RequestBody Resenia resenia,@PathVariable("reseniaId") Long id){
        return this.reseniaService.updateResenia(resenia,id);
    }
    @DeleteMapping(path="{reseniaId}")
    public ResponseEntity<Object> deleteResenia(@PathVariable("reseniaId") Long id){
        return this.reseniaService.deleteResenia(id);
    }
}
