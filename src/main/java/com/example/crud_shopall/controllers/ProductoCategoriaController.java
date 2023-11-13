package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.ProductoCategoria;
import com.example.crud_shopall.services.ProductoCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productoCategoria")
public class ProductoCategoriaController
{
    private final ProductoCategoriaService productoCategoriaService;

    @Autowired
    public ProductoCategoriaController (ProductoCategoriaService productoCategoriaService)
    {
        this.productoCategoriaService = productoCategoriaService;
    }

    @GetMapping("/all")
    public List<ProductoCategoria> getProductoCategoria()
    {
        return this.productoCategoriaService.getProductoCategoria();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> registrarProductoCategoria (@RequestBody List<ProductoCategoria> productoCategorias)
    {
        return this.productoCategoriaService.newProductoCategoria(productoCategorias);
    }

    @PutMapping("/update/{productoCategoriaID}")
    public ResponseEntity <Object> actualizarProductoCategoria(@PathVariable("productoCategoriaID") Long id, @RequestBody List<Long> ids, @RequestBody List<ProductoCategoria> productoCategorias, @RequestBody ProductoCategoria productoCategoria)
    {
        return this.productoCategoriaService.updateProductoCategoria(ids, productoCategorias, productoCategoria);
    }

    @DeleteMapping("/delete/{productoCategoriaID}")
    public ResponseEntity<Object> eliminarProductoCategoria(@PathVariable("productoCategoriaID") Long id)
    {
        return this.productoCategoriaService.deleteProductoCategoria(id);
    }
}
