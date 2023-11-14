package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_cliente")
    private long id_cliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "primer_apellido")
    private String primer_apellido;

    @Column(name = "segundo_apellido")
    private String segundo_apellido;

    @Column(name = "nacimiento")
    private Date nacimiento;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_telefono")
    private Telefono telefono;

    /*@OneToMany(targetEntity = Venta.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonIgnore
    private List<Venta> ventas;*/

    @OneToMany(targetEntity = Resenia.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonIgnore
    private List<Resenia> resenias;
}
