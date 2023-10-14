package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "rol_privilegio")
public class RolPrivilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rol_privilegio;
    private long id_rol;
    private long id_privilegio;
}
