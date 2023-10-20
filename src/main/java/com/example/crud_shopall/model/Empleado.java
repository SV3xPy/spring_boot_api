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

    @ManyToOne
    @JoinColumn(name = "id_telefono")
    private Telefono telefono;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;
}