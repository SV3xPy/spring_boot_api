package com.example.crud_shopall.repositories;

import com.example.crud_shopall.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda,Long> {
}
