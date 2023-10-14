package com.example.crud_shopall.repositories;

import com.example.crud_shopall.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
