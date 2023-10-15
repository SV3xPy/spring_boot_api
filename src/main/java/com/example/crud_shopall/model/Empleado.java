package com.example.crud_shopall.model;

import jakarta.persistence.*;
        import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_empleado;
    private String primer_apellido;
    private String segundo_apellido;
    private String nombre;
    private String rfc;
    private String nacimiento;
    private String curp;
    private long id_telefono;
    private long id_usuario;
    private long id_sexo;
    private long id_tienda;
}