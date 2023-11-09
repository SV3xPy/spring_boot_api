package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Categoria;
import com.example.crud_shopall.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController (CategoriaService categoriaService)
    {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/all")
    public List<Categoria> getCategoria()
    {
        return this.categoriaService.getCategoria();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarCategoria (@RequestBody Categoria categoria)
    {
        return this.categoriaService.newCategoria(categoria);
    }

    @PutMapping("/update/{categoriaID}")
    public ResponseEntity<Object> actualizarCategoria (@RequestBody Categoria categoria, @PathVariable("categoriaID") Long id)
    {
        return this.categoriaService.updateCategoria(categoria, id);
    }

    @DeleteMapping(path = "/delete/{categoriaID}")
    public ResponseEntity<Object> eliminarCategoria(@PathVariable("categoriaID") Long id)
    {
        return this.categoriaService.deleteCategoria(id);
    }
}
