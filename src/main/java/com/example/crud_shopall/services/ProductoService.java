package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Marca;
import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.repositories.MarcaRepository;
import com.example.crud_shopall.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    HashMap<String, Object> datos;
    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, MarcaRepository marcaRepository) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Producto> getProducto ()
    {
        return this.productoRepository.findAll();
    }

    public ResponseEntity<Object> newProducto(Producto producto)
    {
        //Optional<Producto> res = productoRepository.findProductoByProducto(producto.getProducto());
        datos = new HashMap<>();

        /*if (res.isPresent())
        {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe ese producto.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        Optional<Marca> marca = marcaRepository.findById(producto.getMarca().getId_marca());
        if (marca.isEmpty()) {
            datos.put("Error:", true);
            datos.put("Message", "La marca no existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }*/

        productoRepository.save(producto);
        datos.put("Data", producto);
        datos.put("Message", "El producto ha sido insertado con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateProducto(Producto producto, Long id)
    {
        datos = new HashMap<>();
        Optional<Producto> res = this.productoRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe un producto con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        /*Optional<Marca> marca = marcaRepository.findById(producto.getMarca().getId_marca());
        if (marca.isEmpty()) {
            datos.put("Error:", true);
            datos.put("Message", "La marca no existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }*/

        Producto oldProducto = res.get();
        oldProducto.setProducto(producto.getProducto());
        oldProducto.setMarca(producto.getMarca());
        productoRepository.save(oldProducto);
        datos.put("Message", "Se ha actualizado el producto.");
        datos.put("Data", oldProducto);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteProducto (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.productoRepository.existsById(id);
        if (!existe)
        {
            datos.put("Error", true);
            datos.put("Message", "No existe un producto con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        productoRepository.deleteById(id);
        datos.put("Message", "El producto ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
