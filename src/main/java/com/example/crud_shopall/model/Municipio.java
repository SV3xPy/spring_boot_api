/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Anthony Gomez Cabañas
 */
@Getter
@Setter
@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private long id_municipio;
    @Column(name = "municipio")
    private String municipio;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @OneToMany(targetEntity = Localidad.class, fetch = FetchType.LAZY, mappedBy = "municipio")
    @JsonIgnore
    private List<Localidad> localidades;
}
