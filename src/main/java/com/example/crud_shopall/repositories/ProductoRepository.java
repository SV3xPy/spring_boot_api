package com.example.crud_shopall.repositories;
import com.example.crud_shopall.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
}
