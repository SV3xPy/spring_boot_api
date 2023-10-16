/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.crud_shopall.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 
 * @author Anthony Gomez Cabañas
 */
@Getter
@Setter
@Entity
@Table(name = "localidad")
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localidad")
    private long id_localidad;
    @Column(name = "localidad")
    private String localidad;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;
    @OneToMany(targetEntity = Tienda.class, fetch = FetchType.LAZY, mappedBy = "localidad")
    @JsonIgnore
    private List<Tienda> tiendas;
}
