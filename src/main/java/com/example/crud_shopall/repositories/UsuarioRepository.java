package com.example.crud_shopall.repositories;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT e FROM Usuario e WHERE e.correo = :email")
    Optional<Usuario> findOneByEmail(String email);

    @Query("SELECT ur.rol FROM UsuarioRol ur WHERE ur.usuario.id_usuario = :id_usuario")
    List<Rol> findRolesByUsuarioId(@Param("id_usuario") Long id_usuario);

}
