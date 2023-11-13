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
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rol;
    private String rol;

    @OneToMany(targetEntity = RolPrivilegio.class, fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonIgnore
    private List<RolPrivilegio> rolPrivilegios;

    @OneToMany(targetEntity = UsuarioRol.class, fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonIgnore
    private List<UsuarioRol> usuarioRols;
}
