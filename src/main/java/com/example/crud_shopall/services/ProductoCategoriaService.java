package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Categoria;
import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.model.ProductoCategoria;
import com.example.crud_shopall.repositories.CategoriaRepository;
import com.example.crud_shopall.repositories.ProductoCategoriaRepository;
import com.example.crud_shopall.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ProductoCategoriaService {
    HashMap<String, Object> datos;

    private final ProductoCategoriaRepository productoCategoriaRepository;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoCategoriaService(ProductoCategoriaRepository productoCategoriaRepository, ProductoRepository productoRepository, CategoriaRepository categoriaRepository)
    {
        this.productoCategoriaRepository = productoCategoriaRepository;
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProductoCategoria> getProductoCategoria ()
    {
        return this.productoCategoriaRepository.findAll();
    }

    public ResponseEntity<Object> newProductoCategoria(ProductoCategoria productoCategoria)
    {
        datos = new HashMap<>();
        Optional<Producto> producto = productoRepository.findById(productoCategoria.getId_producto());
        if (producto.isEmpty())
        {
            datos.put("Error:", true);
            datos.put("Message", "El producto no existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        Optional<Categoria> categoria = categoriaRepository.findById(productoCategoria.getId_categoria());
        if (categoria.isEmpty())
        {
            datos.put("Error:", true);
            datos.put("Message", "La categoría no existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        productoCategoriaRepository.save(productoCategoria);
        datos.put("Data", producto);
        datos.put("Message", "El producto y su categoría han sido insertados con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateProductoCategoria(ProductoCategoria productoCategoria, Long id)
    {
        datos = new HashMap<>();
        Optional<ProductoCategoria> res = this.productoCategoriaRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe un registro con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        Optional<Producto> producto = productoRepository.findById(productoCategoria.getId_producto());
        if (producto.isEmpty())
        {
            datos.put("Error:", true);
            datos.put("Message", "El producto no existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        Optional<Categoria> categoria = categoriaRepository.findById(productoCategoria.getId_categoria());
        if (categoria.isEmpty())
        {
            datos.put("Error:", true);
            datos.put("Message", "La categoría no existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        ProductoCategoria oldProductoCategoria = res.get();
        oldProductoCategoria.setId_producto(productoCategoria.getId_producto());
        oldProductoCategoria.setId_categoria(productoCategoria.getId_categoria());
        productoCategoriaRepository.save(oldProductoCategoria);
        datos.put("Message", "Se ha actualizado el registro.");
        datos.put("Data", oldProductoCategoria);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteProductoCategoria (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.productoCategoriaRepository.existsById(id);
        if (!existe)
        {
            datos.put("Error", true);
            datos.put("Message", "No existe un registro con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        productoCategoriaRepository.deleteById(id);
        datos.put("Message", "El registro ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
