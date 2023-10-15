package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;
    @Column(name = "id_cliente")
    private String nombre;
    @Column(name = "nombre")
    private String primer_apellido;
    @Column(name = "primer_apellido")
    private String segundo_apellido;
    @Column(name = "segundo_apellido")
    private Date nacimiento;
    @Column(name = "nacimiento")
    private long id_telefono;

    @ManyToOne
    @JoinColumn(name = "id_telefono")
    Telefono telefono;


}
