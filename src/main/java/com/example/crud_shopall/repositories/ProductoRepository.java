package com.example.crud_shopall.repositories;
import com.example.crud_shopall.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long>
{
    Optional<Producto> findProductoByProducto (String producto);
}
