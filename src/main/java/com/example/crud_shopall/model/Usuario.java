package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    private String usuario;
    private String correo;
    private String contrasena;
    private String token;

    @OneToMany(targetEntity = UsuarioRol.class, fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonIgnore
    private List<UsuarioRol> usuarioRols;
}
