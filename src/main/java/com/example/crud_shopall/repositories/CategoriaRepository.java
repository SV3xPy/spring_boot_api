package com.example.crud_shopall.repositories;
import com.example.crud_shopall.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{
    Optional<Categoria> findCategoriaByCategoria(String categoria);
}
