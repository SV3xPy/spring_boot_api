package com.example.crud_shopall.repositories;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.VentaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaStatusRepository extends JpaRepository<VentaStatus,Long> {
}
