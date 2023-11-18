/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author Anthony Gomez Cabañas
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tienda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private long id_tienda;
    @Column(name = "tienda")
    private String tienda;
    @ManyToOne()
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;
    @JsonIgnore
    @OneToMany(targetEntity = TiendaProducto.class, fetch = FetchType.LAZY, mappedBy = "tienda", cascade = CascadeType.REMOVE)
    private List<TiendaProducto> tiendaProducto;
}
