package com.example.crud_shopall.repositories;
import com.example.crud_shopall.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VentaRepository  extends JpaRepository<Venta,Long> {
}