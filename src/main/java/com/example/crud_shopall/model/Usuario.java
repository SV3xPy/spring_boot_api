package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

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
}
