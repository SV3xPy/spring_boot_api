package com.example.crud_shopall.repositories;

import com.example.crud_shopall.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpleadoRepository  extends JpaRepository<Empleado,Long> {
}