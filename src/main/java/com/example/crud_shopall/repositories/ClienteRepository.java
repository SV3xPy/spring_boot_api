package com.example.crud_shopall.repositories;
import com.example.crud_shopall.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
