package com.example.crud_shopall.repositories;
import com.example.crud_shopall.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
    Optional<Marca> findMarcaByMarca(String marca);
}
