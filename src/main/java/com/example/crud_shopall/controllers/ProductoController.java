package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController
{
    private final ProductoService productoService;

    @Autowired
    public ProductoController (ProductoService productoService)
    {
        this.productoService = productoService;
    }

    @GetMapping("/all")
    public List<Producto> getProducto()
    {
        return this.productoService.getProducto();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> registrarProducto (@RequestBody Producto producto)
    {
        return this.productoService.newProducto(producto);
    }

    @PutMapping("/update/{productoID}")
    public ResponseEntity <Object> actualizarProducto (@RequestBody Producto producto, @PathVariable("productoID") Long id)
    {
        return this.productoService.updateProducto(producto, id);
    }

    @DeleteMapping("/delete/{productoID}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable("productoID") Long id)
    {
        return this.productoService.deleteProducto(id);
    }
}
